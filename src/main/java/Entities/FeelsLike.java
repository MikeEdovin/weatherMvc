package Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable
public class FeelsLike  {
    private long id;
    @JsonProperty("day")
    private float dayFeelsLike;
    @JsonProperty("night")
    private float nightFeelsLike;
    @JsonProperty("eve")
    private float eveFeelsLike;
    @JsonProperty("morn")
    private float mornFeelsLike;

}
