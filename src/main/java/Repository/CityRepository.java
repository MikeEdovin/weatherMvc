package Repository;

import Entities.CityData;
import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<CityData,String> {
}
