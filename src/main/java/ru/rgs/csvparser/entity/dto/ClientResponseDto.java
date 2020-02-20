package ru.rgs.csvparser.entity.dto;

import lombok.Data;
import ru.rgs.csvparser.entity.Client;

@Data
public class ClientRequestDto {
    private String clientName;
    private String contractDate;

    public ClientRequestDto(Client client) {
        this.clientName = String.join(" ", client.getFirstName(), client.getMiddleName(), client.getLastName());
        this.contractDate = client.getContractDate().toString();
    }
}
