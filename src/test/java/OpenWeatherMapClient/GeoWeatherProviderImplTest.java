package OpenWeatherMapClient;

import Config.AppConfig;
import Config.CacheConfig;
import Entities.CityData;
import Entities.WeatherData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;
@RestClientTest(GeoWeatherProvider.class)
@ContextConfiguration(classes = {AppConfig.class, CacheConfig.class})
class GeoWeatherProviderImplTest {
    @Autowired
    GeoWeatherProvider provider;
    String cityName;
    double lat,lon;
    @BeforeEach
    void setUp() {
        cityName="Saint Petersburg";
        lat=59.938732;
        lon=30.316229;

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getOneCallAPI() {
        WeatherData data=provider.getOneCallAPI(lat,lon);
        assertEquals(lat,data.getLat(),0.0001);
        assertEquals(lon,data.getLon(),0.0001);

    }


    @Test
    void directGeoApiCall() {
        CityData[] data=provider.directGeoApiCall(cityName);
        for(CityData item:data){
            assert item instanceof CityData;
        }

    }

}