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
@IdClass(WeatherId.class)
public class WeatherData {
    @Id
    private float lat;
    @Id
    private float lon;
    private String timezone;
    @Embedded
    private Current current;
    @Embedded
    @ElementCollection
    @OrderColumn(name="dt")
    private Daily[] daily;
}


