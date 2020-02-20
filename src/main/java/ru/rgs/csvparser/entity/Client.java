package ru.rgs.csvparser.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Client {
    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDate contractDate;
    private String scoring;
}
