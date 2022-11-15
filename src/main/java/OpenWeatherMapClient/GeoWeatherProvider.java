package OpenWeatherMapClient;

import Entities.CityData;
import Entities.WeatherData;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;


public interface GeoWeatherProvider {

    String getOneCallAPI(Double latitude, Double longitude);
    WeatherData getWeatherData(String response) throws JsonProcessingException;
    List<CityData> getCityData(String response) throws JsonProcessingException;
    String directGeoApiCall(String city);
    String reverseGeoApiCall(Double latitude, Double longitude);
    void clearWeatherCache(long lat);
}
