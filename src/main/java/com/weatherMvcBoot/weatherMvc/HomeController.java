package com.weatherMvcBoot.weatherMvc;

import Entities.CityData;
import OpenWeatherMapClient.GeoWeatherProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

//@Controller
@Controller

public class HomeController {
    @Autowired
    GeoWeatherProvider geoWeatherProvider;

    @GetMapping("/cities")
    String getCities(Model model) throws JsonProcessingException {
        List<CityData> cities=geoWeatherProvider.getCityData(geoWeatherProvider.directGeoApiCall("Питер"));
        model.addAttribute("citiesList",cities);
        return "cities";}
}
