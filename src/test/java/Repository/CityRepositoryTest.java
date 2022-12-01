package Repository;

import Config.InfrastructureConfig;
import Entities.CityData;
import Entities.WeatherId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@ContextConfiguration(classes = InfrastructureConfig.class)
class CityRepositoryTest {
    @Autowired
    CityRepository repository;

    CityData city1,city2,city3;

    @BeforeEach
    void setUp() {
        city1=new CityData("Haifa",30.222,30.223,"Israel","North");
        city2=new CityData("Tel Aviv",30.333,30.444,"Israel","Center");
        city3=new CityData("Akko",30.111,30.112,"Israel","North");
        repository.saveAll(List.of(city1,city2));
        repository.save(city3);
    }
    @Test
    void testFindAll() {
        assertEquals(List.of(city1,city2,city3), repository.findAll());
    }
    @Test
    void testFindById(){
        assertEquals(city1,repository.findById(new WeatherId(city1.getLat(),city1.getLon())).get());
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }
}