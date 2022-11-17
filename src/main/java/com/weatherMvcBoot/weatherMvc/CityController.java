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
@RequestMapping("/cities")
public class CityController {
    @Autowired
    GeoWeatherProvider geoWeatherProvider;
    @Autowired
    CityService cityService;

    @GetMapping("/enterCityName")
    String openCityEnteringForm() {
        return "enterCityName";
    }

    @PostMapping("/enterCityName")
    String requestCities(String city) throws JsonProcessingException {
        List<CityData> cities = geoWeatherProvider.getCityData(geoWeatherProvider.directGeoApiCall(city));
        cityService.saveAll(cities);
        return "redirect:cities/chooseCity";
    }
    @GetMapping("/chooseCity")
    String getCities(Model model){
        System.out.println("redirect");
        Iterable<CityData> cities=cityService.getAllCities();
        model.addAttribute("citiesList",cities);
        return "cities";
    }

}
