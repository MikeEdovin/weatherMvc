package com.weatherMvcBoot.weatherMvc;

import Entities.CityData;
import OpenWeatherMapClient.GeoWeatherProvider;
import Service.CityService;
import Service.WeatherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//@Controller
@Controller
//@RequestMapping("/cities")
public class CityController {
    @Autowired
    GeoWeatherProvider geoWeatherProvider;
    @Autowired
    CityService cityService;

    @GetMapping("/cities/enterCityName")
    String openCityEnteringForm() {
        return "enterCityName";
    }

    @PostMapping("/cities/enterCityName")
    String requestCities(String city) throws JsonProcessingException {
        List<CityData> cities = geoWeatherProvider.getCityData(geoWeatherProvider.directGeoApiCall(city));
        cityService.saveAll(cities);
        return "redirect:chooseCity";
    }
    @GetMapping("/cities/chooseCity")
    String getCities(Model model){
        Iterable<CityData> cities=cityService.getAllCities();
        model.addAttribute("citiesList",cities);
        return "cities";
    }

}
