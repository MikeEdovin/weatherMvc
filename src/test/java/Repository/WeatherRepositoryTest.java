package Repository;

import Config.InfrastructureConfig;
import Entities.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@ContextConfiguration(classes = InfrastructureConfig.class)
class WeatherRepositoryTest {
    @Autowired
    WeatherRepository repository;
    WeatherData data1,data2,data3;

    @BeforeEach
    void setUp() {
        Daily daily=new Daily(100000,1000345,105656,100009,10339,10.2f,
                new Temp(122,23.2f,23,24,15,18,16),
                new FeelsLike(1222,23,23,23,23),
        1015,23,12.1f,6,23,23,null,23,44,12,3);
        Daily[] dailies={daily,null,null,null,null,null,null};
       data1=new WeatherData(30.111,30.111,"Asia",
               new Current(1000000,1000222,1033333,
                       22.2f,23.2f,1012,78,
                       12.1f,3,23,1000,2,
                       35,22,null),dailies);
       data2=new WeatherData(30.222,30.222,"Asia",
               new Current(1000000,1000222,1033333,
                       22.2f,23.2f,1012,78,
                       12.1f,3,23,1000,2,
                       35,22,null),dailies);
       data3=new WeatherData(30.333,30.333,"Asia",
               new Current(1000000,1000222,1033333,
                       22.2f,23.2f,1012,78,
                       12.1f,3,23,1000,2,
                       35,22,null),dailies);
       repository.saveAll(List.of(data1,data2));
       repository.save(data3);
    }
    @Test
    void testFindAll(){
        assertEquals(List.of(data1,data2,data3),repository.findAll());
    }
    @Test
    void testFindById(){
        assertEquals(data1,repository.findById(new WeatherId(data1.getLat(), data1.getLon())).get());
    }

    @AfterEach
    void tearDown() {
    }
}