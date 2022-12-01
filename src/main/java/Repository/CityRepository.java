package Repository;

import Entities.CityData;
import Entities.WeatherId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<CityData, WeatherId> {
}
