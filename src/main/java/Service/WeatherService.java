package Service;

import Entities.WeatherData;
import Entities.WeatherId;

import java.util.Optional;

public interface WeatherService {
    void save(WeatherData weatherData);
    Optional<WeatherData>getWeatherDataById(WeatherId id);
}
