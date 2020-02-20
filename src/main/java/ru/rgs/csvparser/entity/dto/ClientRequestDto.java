package ru.rgs.csvparser.entity.dto;

import lombok.Data;
import ru.rgs.csvparser.entity.Client;
import ru.rgs.csvparser.service.util.ClientHelper;

@Data
public class ClientRequestDto {
    private String clientName;
    private String contractDate;

    public ClientRequestDto(Client client) {
        this.clientName = ClientHelper.returnFullName(client);
        this.contractDate = client.getContractDate().toString();
    }
}
