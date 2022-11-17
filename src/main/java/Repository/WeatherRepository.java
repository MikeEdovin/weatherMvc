package Repository;

import Entities.WeatherData;
import Entities.WeatherId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends CrudRepository<WeatherData, WeatherId> {
}
