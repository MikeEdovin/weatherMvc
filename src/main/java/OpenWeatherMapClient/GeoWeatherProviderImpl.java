package OpenWeatherMapClient;


import Entities.CityData;
import Entities.WeatherData;
import Entities.WeatherId;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
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
    public WeatherData getOneCallAPI(Double latitude, Double longitude) {
        String APP_ID = System.getenv("WEATHER_MAP_APP_ID");
        final String URL_API = "https://api.openweathermap.org/data/2.5/onecall?";
        String url = URL_API + "lat=" + latitude + "&lon=" + longitude
                + "&exclude=minutely,hourly" + "&appid=" + APP_ID + "&units=metric";
        return this.restTemplate.getForObject(url, WeatherData.class);
    }
    @Override
    public CityData[] directGeoApiCall(String city) {
        final String URL_API = "http://api.openweathermap.org/geo/1.0/direct?q=";
        String APP_ID = System.getenv("WEATHER_MAP_APP_ID");
        String url = URL_API + city + ",ru_RU" + "&limit=3&appid=" + APP_ID;
        return this.restTemplate.getForObject(url,CityData[].class);
    }



}
