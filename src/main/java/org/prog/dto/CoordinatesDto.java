package org.prog.dto;

import lombok.Data;

@Data
public class CoordinatesDto {
    private String latitude;
    private String longitude;

    // Getter для latitude
    public String getLatitude() {
        return latitude;
    }

    // Getter для longitude
    public String getLongitude() {
        return longitude;
    }
}
