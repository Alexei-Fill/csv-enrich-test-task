package ru.rgs.csvparser.service.util;

import ru.rgs.csvparser.entity.Client;
import ru.rgs.csvparser.entity.dto.ClientResponseDto;

public final class ClientHelper {
    private ClientHelper() {
    }

    public static String returnFullName(Client client) {
        return String.join(" ", client.getFirstName(), client.getMiddleName(), client.getLastName());
    }

    public static String getClientScoring(ClientResponseDto clientResponseDto) {
        String score;
        switch (clientResponseDto.getStatus().toUpperCase()){
            case "COMPLETED":
                score = String.valueOf(Double.parseDouble(clientResponseDto.getScoringValue()));
                break;
            case "NOT_FOUND":
                score = "не найден";
                break;
            case "FAILED":
                default:
                    score = "ошибка обработки";
        }
        return score;
    }
}
