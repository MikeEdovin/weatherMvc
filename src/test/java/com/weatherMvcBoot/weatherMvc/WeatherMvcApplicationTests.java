package com.weatherMvcBoot.weatherMvc;

import Entities.WeatherData;
import OpenWeatherMapClient.GeoWeatherProvider;
import OpenWeatherMapClient.GeoWeatherProviderImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.util.Assert;

@SpringBootTest
class WeatherMvcApplicationTests {

	@Test
	void contextLoads() throws JsonProcessingException {
		RestTemplateBuilder restTemplateBuilder=new RestTemplateBuilder();
		GeoWeatherProvider geoWeatherProvider=new GeoWeatherProviderImpl(restTemplateBuilder);
		String res= geoWeatherProvider.getOneCallAPI(59.938732, 30.316229);
		System.out.println(res);
		WeatherData weatherData=geoWeatherProvider.getWeatherData(res);
		System.out.println(weatherData.getLat()+weatherData.getTimezone()+weatherData.getCurrent().getCurrentPressure());
	}

}
