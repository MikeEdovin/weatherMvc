package OpenWeatherMapClient;

import Entities.CityData;
import Entities.WeatherData;
import Entities.WeatherId;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;


public interface GeoWeatherProvider {

    WeatherData getOneCallAPI(Double latitude, Double longitude);
    CityData[] directGeoApiCall(String city);

}
