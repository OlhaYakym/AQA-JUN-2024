package org.prog.dto;

import lombok.Data;

@Data
public class PersonDto {
    private String gender;
    private String nat;
    private NameDto name;
    private LocationDto location;

    // Getter для gender
    public String getGender() {
        return gender;
    }

    // Getter для location
    public LocationDto getLocation() {
        return location;
    }
}
