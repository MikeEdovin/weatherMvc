package com.weatherMvcBoot.weatherMvc;

import Config.AppConfig;
import Config.CacheConfig;
import Config.InfrastructureConfig;
import Entities.WeatherData;
import OpenWeatherMapClient.GeoWeatherProvider;
import OpenWeatherMapClient.GeoWeatherProviderImpl;
import Repository.CityRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
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
				ApplicationContext context=SpringApplication.run(WeatherMvcApplication.class, args);
				GeoWeatherProvider geoWeatherProvider=context.getBean(GeoWeatherProvider.class);
				try {
					String res=
							geoWeatherProvider.getOneCallAPI(59.938732, 30.316229);
					System.out.println(res);
					WeatherData weatherData=geoWeatherProvider.getWeatherData(res);
				}catch (Exception e) {
					System.out.println("exception");
					e.printStackTrace();
				}


				}

	}



