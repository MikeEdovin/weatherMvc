package Service;

import Entities.WeatherData;
import Entities.WeatherId;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface WeatherService {
    void save(WeatherData weatherData);
    Optional<WeatherData>getWeatherDataById(WeatherId id);
}
