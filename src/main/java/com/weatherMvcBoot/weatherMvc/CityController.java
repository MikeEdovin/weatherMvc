package com.weatherMvcBoot.weatherMvc;

import Entities.*;
import OpenWeatherMapClient.GeoWeatherProvider;
import Service.CityService;
import Service.WeatherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.*;

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
    String requestCities(CityData city, Model model) {
        try{
            cityService.deleteAll();
            CityData[] cities= geoWeatherProvider.directGeoApiCall(city.getName());
            System.out.println("city"+cities[0].getName());
            cityService.saveAll(List.of(cities));
            return "redirect:chooseCity";
        }catch(HttpClientErrorException e){
            e.printStackTrace();
            model.addAttribute("exception", e);
            return "error";
        }catch (Exception exc){
            exc.printStackTrace();
            model.addAttribute("exception", exc);
            return "error";
        }


    }
    @GetMapping("/cities/chooseCity")
    String getCities(Model model){
        Iterable<CityData> cities=cityService.getAllCities();
        System.out.println("redirected");
        model.addAttribute("citiesList", cities);
        return "cities";
    }
    @PostMapping("/cities/chooseCity")
    RedirectView requestForecast(CityData city, RedirectAttributes attributes) throws JsonProcessingException {
        WeatherData weatherData=geoWeatherProvider.getOneCallAPI(city.getLat(), city.getLon());
        weatherData.setLatLon(city.getLat(), city.getLon());//API returns different values for geo and weather requests
        attributes.addAttribute("name",city.getName());
        attributes.addAttribute("lat",city.getLat());
        attributes.addAttribute("lon",city.getLon());
        weatherService.save(weatherData);
        return new RedirectView("/cities/weather");
    }

    @GetMapping("/cities/weather")
    String getForecast(Model model,@RequestParam(name="name")String name,
                       @RequestParam(name="lat") double lat,
                       @RequestParam(name="lon") double lon){
        WeatherId weatherId=new WeatherId(lat,lon);
        try {
            Optional<WeatherData> weatherData = weatherService.getWeatherDataById(weatherId);
            Current current = weatherData.get().getCurrent();
            Daily[] daily = weatherData.get().getDaily();
            model.addAttribute("name", name);
            model.addAttribute("current", current);
            model.addAttribute("daily", daily);
        }catch(NoSuchElementException e){
            model.addAttribute("exception",e);
              return "error";
        }
        return "weather";
    }



}
