package ru.rgs.csvparser.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientResponseDto {
    private String status;
    private String scoringValue;
}
