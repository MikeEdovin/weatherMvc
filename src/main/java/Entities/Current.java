package Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Current {
    @Id
    private long dt;
    private long sunrise;
    private long sunset;
    private float temp;
    @JsonProperty("feels_like")
    private float feelsLike;
    private int pressure;
    private int humidity;
    @JsonProperty("dew_point")
    private float dewPoint;
    private float uvi;
    private int clouds;
    private int visibility;
    @JsonProperty("wind_speed")
    private float windSpeed;
    @JsonProperty("wind_degree")
    private int windDegree;
    @JsonProperty("wind_gust")
    private float windGust;
    private Weather[] weather;


}
