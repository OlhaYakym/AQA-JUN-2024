package org.prog.dto;

import lombok.Data;

@Data
public class StreetDto {
    private int number;
    private String name;

    // Getter для number
    public int getNumber() {
        return number;
    }

    // Getter для name
    public String getName() {
        return name;
    }
}
