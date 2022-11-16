package Service;

import Entities.CityData;

import java.util.List;
import java.util.Optional;

public interface CityService {
    void save(CityData city);
    void saveAll(List<CityData>cityList);
    Optional<CityData> getCityByName(String name);
    Iterable<CityData> getAllCities();
}
