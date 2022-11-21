package Service;

import Entities.CityData;
import Repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CityServiceImpl implements CityService{
    @Autowired
    CityRepository cityRepository;
    @Override
    public void save(CityData city) {
        cityRepository.save(city);
    }

    @Override
    public void saveAll(List<CityData> cityList) {
        cityRepository.saveAll(cityList);
    }

    @Override
    public Optional<CityData> getCityByName(String name) {
        return cityRepository.findById(name);
    }

    @Override
    public Iterable<CityData> getAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public void deleteAll() {
        cityRepository.deleteAll();
    }
}
