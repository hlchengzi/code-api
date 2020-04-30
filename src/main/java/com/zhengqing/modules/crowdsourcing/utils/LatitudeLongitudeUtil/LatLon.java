package com.zhengqing.modules.crowdsourcing.utils.LatitudeLongitudeUtil;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * 描述:经度纬度的封装
 *
 * @author xcxu
 * @create 2020-04-16 12:49
 */
@Data
public class LatLon implements Serializable {
    double lat;
    double lon;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        LatLon latLon = (LatLon) o;
        return Double.compare(latLon.lat, lat) == 0 &&
                Double.compare(latLon.lon, lon) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lat, lon);
    }
}
