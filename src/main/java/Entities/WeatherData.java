package Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

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
    private Daily[] daily;
}


