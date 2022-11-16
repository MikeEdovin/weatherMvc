package Config;
import OpenWeatherMapClient.GeoWeatherProvider;
import OpenWeatherMapClient.GeoWeatherProviderImpl;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.*;

@Configuration
@EnableAspectJAutoProxy
@EnableAutoConfiguration

public class AppConfig {

    @Bean("restTemplateBuilder")
    public RestTemplateBuilder restTemplateBuilder(){
        return new RestTemplateBuilder();
    }
    @Bean
    public GeoWeatherProvider geoWeatherProvider(RestTemplateBuilder restTemplateBuilder){
        return new GeoWeatherProviderImpl(restTemplateBuilder);
    }



}
