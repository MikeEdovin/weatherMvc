package Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable
public class Temp {
    private long tempId;
    @JsonProperty("day")
    private float dayTemp;
    @JsonProperty("min")
    private float minTemp;
    @JsonProperty("max")
    private float maxTemp;
    @JsonProperty("night")
    private float nightTemp;
    @JsonProperty("eve")
    private float eveTemp;
    @JsonProperty("morn")
    private float mornTemp;



}
