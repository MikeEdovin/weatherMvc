package com.weatherMvcBoot.weatherMvc;

import Config.AppConfig;
import Config.CacheConfig;
import Config.InfrastructureConfig;
import Config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;


@SpringBootApplication
@EnableAutoConfiguration

@Import({AppConfig.class, CacheConfig.class, InfrastructureConfig.class, SecurityConfig.class})
public class WeatherMvcApplication {

	public static void main(String[] args) {
				SpringApplication.run(WeatherMvcApplication.class, args);



				}

	}



