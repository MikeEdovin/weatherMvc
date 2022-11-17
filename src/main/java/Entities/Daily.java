package Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Daily {
    @Id
    private long dt;
    private long sunrise;
    private long sunset;
    private long moonrise;
    private long moonset;
    @JsonProperty("moon_phase")
    private float moonPhase;
    @Embedded
    //@EmbeddedId
    private Temp temp;
    @JsonProperty("feels_like")
    @Embedded
    //@EmbeddedId
    private FeelsLike feelsLike;
    private int pressure;
    private int humidity;
    @JsonProperty("dew_point")
    private float dewPoint;
    @JsonProperty("wind_speed")
    private float windSpeed;
    @JsonProperty("wind_degree")
    private int windDegree;
    @JsonProperty("wind_gust")
    private float windGust;
    private Weather[] weather;
    private int clouds;
    private float pop;
    private float rain;
    private float uvi;
}
