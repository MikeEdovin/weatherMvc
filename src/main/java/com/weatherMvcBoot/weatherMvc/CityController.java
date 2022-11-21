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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
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
    String requestCities(String city, Model model) throws JsonProcessingException {
        cityService.deleteAll();
        String res=geoWeatherProvider.directGeoApiCall(city);
        if(res.equalsIgnoreCase("[]")){
            model.addAttribute("exception", new NoSuchElementException("Nothing was found"));
            return "error";
        }
        else{
            List<CityData> cities = geoWeatherProvider.getCityData(geoWeatherProvider.directGeoApiCall(city));
            cityService.saveAll(cities);
            return "redirect:chooseCity";
        }
    }
    @GetMapping("/cities/chooseCity")
    String getCities(Model model){
        Iterable<CityData> cities=cityService.getAllCities();
        model.addAttribute("citiesList", cities);
        return "cities";
    }
    @PostMapping("/cities/chooseCity")
    RedirectView requestForecast(CityData city, RedirectAttributes attributes) throws JsonProcessingException {
        String res=geoWeatherProvider.getOneCallAPI(city.getLat(), city.getLon());
        WeatherData weatherData=geoWeatherProvider.getWeatherData(res);
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
