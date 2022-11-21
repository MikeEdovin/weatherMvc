package Service;

import Entities.WeatherData;
import Entities.WeatherId;
//import Repository.WeatherRepository;
import Repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

public class WeatherServiceImpl implements WeatherService{
    @Autowired
    WeatherRepository weatherRepository;
    @Override
    public void save(WeatherData weatherData) {
        weatherRepository.save(weatherData);
    }

    @Override
    public Optional<WeatherData> getWeatherDataById(WeatherId id) {
        Iterable<WeatherData> all= weatherRepository.findAll();
        for(WeatherData item:all){
            System.out.println("item "+item.getLat()+" "+item.getLon());
            System.out.println("id "+id.getLat()+" "+id.getLon());
        }

        return weatherRepository.findById(id);
    }


}


