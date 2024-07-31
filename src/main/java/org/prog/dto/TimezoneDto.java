package org.prog.dto;

import lombok.Data;

@Data
public class TimezoneDto {
    private String offset;
    private String description;

    // Getter для offset
    public String getOffset() {
        return offset;
    }

    // Getter для description
    public String getDescription() {
        return description;
    }
}
