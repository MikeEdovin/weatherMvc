package com.weatherMvcBoot.weatherMvc;

import Entities.*;
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
import org.springframework.web.servlet.ModelAndView;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

//@Controller
@Controller
//@RequestMapping("/cities")
public class CityController {
    @Autowired
    GeoWeatherProvider geoWeatherProvider;
    @Autowired
    CityService cityService;

    @Autowired
    WeatherService weatherService;



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
    @PostMapping("/cities/chooseCity")
    String requestForecast(CityData city,Model model) throws JsonProcessingException {
        System.out.println("city "+city.getLat()+" "+city.getLon());
        String res=geoWeatherProvider.getOneCallAPI(city.getLat(), city.getLon());
        WeatherData weatherData=geoWeatherProvider.getWeatherData(res);
        Current current=weatherData.getCurrent();
        Daily[] daily= weatherData.getDaily();
        model.addAttribute("city",city);
        model.addAttribute("current",current);
        model.addAttribute("daily",daily);
        weatherService.save(weatherData);
        //return "redirect:weather";
        return "weather";
    }
    @GetMapping("/cities/weather")
    String getForecast(Model model){
        System.out.println("got it");
        CityData cityData= (CityData) model.getAttribute("city");
        WeatherId weatherId=new WeatherId( cityData.getLat(),
                cityData.getLon());

        Optional<WeatherData> weatherData=weatherService.getWeatherDataById(weatherId);
        Current current= weatherData.get().getCurrent();
        Daily[] daily= weatherData.get().getDaily();
        System.out.println("daily" +daily[0].getFeelsLike());
        model.addAttribute("name",cityData.getName());
        model.addAttribute("current",current);
        model.addAttribute("daily",daily);


        return "weather";
    }

}
