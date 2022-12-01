package Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@IdClass(WeatherId.class)
@Table(name="weather_data")

public class WeatherData {


    @Id
    private double lat;
    @Id
    private double lon;
    private String timezone;
    @Embedded
    private Current current;
    @Embedded
    @ElementCollection
    @JoinTable(name="daily")
    @OrderColumn(name="daily_index")
    private Daily[] daily;




    public void setLatLon(double lat, double lon){
        this.lat=lat;
        this.lon=lon;
    }


}


