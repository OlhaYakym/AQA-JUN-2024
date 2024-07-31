package org.prog.dto;

import lombok.Data;

@Data
public class LocationDto {
    private StreetDto street;
    private String city;
    private String state;
    private String country;
    private String postcode;
    private CoordinatesDto coordinates;
    private TimezoneDto timezone;

    // Getter для city
    public String getCity() {
        return city;
    }

    // Getter для coordinates
    public CoordinatesDto getCoordinates() {
        return coordinates;
    }

    // Getter для timezone
    public TimezoneDto getTimezone() {
        return timezone;
    }

}
