package Config;

import Service.CityService;
import Service.CityServiceImpl;
import Service.WeatherServiceImpl;
import Service.WeatherService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfrastructureConfig {
    @Bean
    public CityService cityService(){return new CityServiceImpl();
    }
    @Bean
    public WeatherService weatherService(){return new WeatherServiceImpl();
    }
}
