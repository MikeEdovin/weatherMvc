package Entities;

import java.io.Serializable;

public class WeatherId implements Serializable {
    private float lat,lon;

    public WeatherId(float lat,float non){
        this.lat=lat;
        this.lon=lon;
    }
    public WeatherId(){}

    @Override
    public boolean equals(Object o){
        if(!(o instanceof WeatherId)){
            return false;
        }
        else{
            WeatherId id=(WeatherId) o;
            return this.lat==id.lat&&this.lon==id.lon;
        }
    }
    @Override
    public int hashCode(){
        int hash=17;
        hash= 31 * hash + Float.floatToIntBits(lat);
        hash=31*hash+Float.floatToIntBits(lon);
        return hash;
    }
}
