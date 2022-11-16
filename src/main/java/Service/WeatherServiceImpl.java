package Service;

import Entities.WeatherData;
import Entities.WeatherId;
import Repository.WeatherRepository;

import java.util.Optional;

public class WeatherServiceImpl implements WeatherService{

    WeatherRepository weatherRepository;
    @Override
    public void save(WeatherData weatherData) {
        weatherRepository.save(weatherData);
    }

    @Override
    public Optional<WeatherData> getWeatherDataById(WeatherId id) {
        return weatherRepository.findById(id);
    }
}
