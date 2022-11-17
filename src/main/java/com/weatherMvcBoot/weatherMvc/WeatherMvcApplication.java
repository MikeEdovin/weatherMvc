package com.weatherMvcBoot.weatherMvc;

import Config.AppConfig;
import Config.CacheConfig;
import Config.InfrastructureConfig;
import OpenWeatherMapClient.GeoWeatherProvider;
import OpenWeatherMapClient.GeoWeatherProviderImpl;
import Repository.CityRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;

@SpringBootApplication
@EnableAutoConfiguration

@Import({AppConfig.class, CacheConfig.class, InfrastructureConfig.class})
public class WeatherMvcApplication {

	public static void main(String[] args) {
				SpringApplication.run(WeatherMvcApplication.class, args);

	}


}
