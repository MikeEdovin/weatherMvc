package Config;
import com.weatherMvcBoot.weatherMvc.HomeController;
import OpenWeatherMapClient.GeoWeatherProvider;
import OpenWeatherMapClient.GeoWeatherProviderImpl;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.*;

@Configuration
@EnableAspectJAutoProxy
@EnableAutoConfiguration
@ComponentScan(basePackages = "src/main/java/Controllers")
//@Import({CacheConfig.class})
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
