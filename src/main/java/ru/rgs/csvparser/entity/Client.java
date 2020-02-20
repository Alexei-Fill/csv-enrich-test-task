package ru.rgs.csvparser.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Client {
    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDate contractDate;
    private String scoring;

    public Client(String firstName, String lastName, String middleName, LocalDate contractDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.contractDate = contractDate;
    }
}
