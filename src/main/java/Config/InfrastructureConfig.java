package Config;

import Entities.CityData;
import Repository.CityRepository;
import Repository.WeatherRepository;
import Service.CityService;
import Service.CityServiceImpl;
import Service.WeatherServiceImpl;
import Service.WeatherService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(basePackageClasses = {CityRepository.class,WeatherRepository.class})
@EntityScan(basePackages = {"Entities"})
public class InfrastructureConfig {
    @Bean
    public CityService cityService(){return new CityServiceImpl();
    }
    @Bean
    public WeatherService weatherService(){return new WeatherServiceImpl();
    }
    
}
