package ru.rgs.csvparser.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.rgs.csvparser.entity.enums.ResponseStatus;

@Data
@AllArgsConstructor
public class ClientResponseDto {
    private ResponseStatus status;
    private String scoringValue;
}
