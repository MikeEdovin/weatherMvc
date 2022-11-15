package Controllers;

import Entities.CityData;
import OpenWeatherMapClient.GeoWeatherProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    GeoWeatherProvider geoWeatherProvider;

    @GetMapping(value = "/home")
    public String getCities(Model model) throws JsonProcessingException {
        List<CityData> cities=geoWeatherProvider.getCityData(geoWeatherProvider.directGeoApiCall("Piter"));
        for(CityData item:cities){
            model.addAttribute(item.getName(),item);
        }

        return "cities";}
}
