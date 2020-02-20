package ru.rgs.csvparser.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rgs.csvparser.client.ServiceFeignClient;
import ru.rgs.csvparser.entity.Client;
import ru.rgs.csvparser.entity.dto.ClientRequestDto;
import ru.rgs.csvparser.entity.dto.ClientResponseDto;
import ru.rgs.csvparser.service.util.ClientHelper;
import ru.rgs.csvparser.service.util.CsvHelper;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static ru.rgs.csvparser.service.util.ClientHelper.returnFullName;

@Slf4j
@Service
public class CsvParserServiceImpl implements CsvParserService {

    @Autowired
    private ServiceFeignClient serviceFeignClient;

    @Override
    public Path processCsv(Path source) {
        String filePath = "";
        try {
            List<Client> clients = CsvHelper.readFromFile(source, true).stream().map(this::toClient).collect(Collectors.toList());
            clients.parallelStream().forEach(this::requestForClientScore);
            filePath = writeToFile(clients);
        } catch (IOException e) {
           log.error(e.getMessage());
        }
        return Paths.get(filePath);
    }

    private void requestForClientScore(Client client) {
        ClientResponseDto clientResponseDto = serviceFeignClient.getClientScore(new ClientRequestDto(client));
        client.setScoring(ClientHelper.getClientScoring(clientResponseDto));
    }

    private Client toClient(List<String> row) {
        return new Client(row.get(0).toUpperCase(), row.get(1).toUpperCase(), row.get(2).toUpperCase(),
                LocalDate.parse(row.get(3), DateTimeFormatter.ISO_DATE));
    }

    private String writeToFile(List<Client> clients) throws IOException {
        List<String> rows = clients.stream().map(this::toCsvRow).collect(Collectors.toList());

        rows.add(0, "CLIENT_NAME,CONTRACT_DATE,SCORING");
        String filePath = generateOutputFileName();
        CsvHelper.writeFile(rows, filePath);
        return filePath;
    }

    private String generateOutputFileName() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd__HH_mm_ss");
        return String.format("csv-processing-output/output-%s-af.csv", LocalDateTime.now().format(formatter));
    }

    private String toCsvRow(Client client) {
        return String.join("," ,returnFullName(client), client.getContractDate().toString(),
                client.getScoring());
    }
}
