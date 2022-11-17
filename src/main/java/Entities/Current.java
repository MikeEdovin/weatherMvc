package Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Current {
    @Id
    @JsonProperty("dt")
    private long currentDt;
    @JsonProperty("sunrise")
    private long currentSunrise;
    @JsonProperty("sunset")
    private long currentSunset;
    private float temp;
    @JsonProperty("feels_like")
    private float feelsLike;
    @JsonProperty("pressure")
    private int currentPressure;
    @JsonProperty("humidity")
    private int currentHumidity;
    @JsonProperty("dew_point")
    private float currentDewPoint;
    @JsonProperty("uvi")
    private float currentUvi;
    @JsonProperty("clouds")
    private int currentClouds;
    private int visibility;
    @JsonProperty("wind_speed")
    private float currentWindSpeed;
    @JsonProperty("wind_degree")
    private int currentWindDegree;
    @JsonProperty("wind_gust")
    private float currentWindGust;
    @JsonProperty("weather")
    private Weather[] currentWeather;


}
