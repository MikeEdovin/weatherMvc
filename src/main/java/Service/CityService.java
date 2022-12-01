package Service;

import Entities.CityData;
import Entities.WeatherId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface CityService {
    void save(CityData city);
    void saveAll(List<CityData>cityList);
    Optional<CityData> getCityById(WeatherId weatherId);
    Iterable<CityData> getAllCities();

    void deleteAll();
}
