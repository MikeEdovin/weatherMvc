package Service;

import Entities.CityData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface CityService {
    void save(CityData city);
    void saveAll(List<CityData>cityList);
    Optional<CityData> getCityByName(String name);
    Iterable<CityData> getAllCities();
    void deleteAll();
}
