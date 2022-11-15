package OpenWeatherMapClient;


import Entities.CityData;
import Entities.WeatherData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.client.RestTemplate;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

public class GeoWeatherProviderImpl implements GeoWeatherProvider {
    @Autowired
    private CacheManager cacheManager;
    private final RestTemplate restTemplate;

    public GeoWeatherProviderImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public String getOneCallAPI(Double latitude, Double longitude) {
        String APP_ID = System.getenv("WEATHER_MAP_APP_ID");
        final String URL_API = "https://api.openweathermap.org/data/2.5/onecall?";
        String url = URL_API + "lat=" + latitude + "&lon=" + longitude
                + "&exclude=minutely,hourly" + "&appid=" + APP_ID + "&units=metric";
        return this.restTemplate.getForObject(url, String.class);
    }
    
    @Override
    @Cacheable(cacheNames = "weatherDataCache",key="#weatherData.lat" )
    public WeatherData getWeatherData(String response) throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        WeatherData weatherData=objectMapper.readValue(response,WeatherData.class);
        return weatherData;
    }

    @Override
    public List<CityData> getCityData(String response) throws JsonProcessingException {
        System.out.println("called cityData");
        ObjectMapper objectMapper=new ObjectMapper();
        CollectionType javaType = objectMapper.getTypeFactory()
                .constructCollectionType(List.class, CityData.class);
        return objectMapper.readValue(response, javaType);
    }

    @Override
    public String directGeoApiCall(String city) {
        final String URL_API = "http://api.openweathermap.org/geo/1.0/direct?q=";
        String APP_ID = System.getenv("WEATHER_MAP_APP_ID");
        String url = URL_API + city + ",ru_RU" + "&limit=3&appid=" + APP_ID;
        return this.restTemplate.getForObject(url, String.class);
    }

    @Override
    public String reverseGeoApiCall(Double latitude, Double longitude) {
        final String URL_API = "http://api.openweathermap.org/geo/1.0/reverse?lat=";
        final String APP_ID = System.getenv("WEATHER_MAP_APP_ID");
        String url = URL_API + latitude + "&lon=" + longitude + "&limit=1&appid=" + APP_ID;
        return this.restTemplate.getForObject(url, String.class);
    }

    @Override
    public void clearWeatherCache(long lat){
        Cache cache= cacheManager.getCache("currentWeatherCache");
        assert cache != null;
        WeatherData cacheWeather= cache.get(lat,WeatherData.class);
        if(cacheWeather!=null) {
            ZonedDateTime zdtNow = ZonedDateTime.now(ZoneId.of(cacheWeather.getTimezone()));
            Long now = zdtNow.toEpochSecond();
            long timeOfUpdate = cacheWeather.getCurrent().getDt();
            boolean result=now - timeOfUpdate > 3600;
            if(result){
                evictSingleCacheValue("weatherDataCache", String.valueOf(lat));
            }
        }


    }
    public void evictSingleCacheValue(String cacheName, String cacheKey) {
        Objects.requireNonNull(cacheManager.getCache(cacheName)).evict(cacheKey);
    }

}
