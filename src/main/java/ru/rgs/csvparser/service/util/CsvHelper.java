package ru.rgs.csvparser.service.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class CsvHelper {

    private CsvHelper() {
    }

    public static void writeFile(List<String> rows, String outputFilePath) throws IOException {
        try(OutputStream os = new FileOutputStream(outputFilePath)) {
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
            for (String rowData : rows) {
                writer.println(rowData);
            }
            writer.flush();
        }
    }
    public static  List<List<String>> readFromFile(Path source, boolean skipColumnTitle) throws IOException {
        List<List<String>> rows = new ArrayList<>();
        try (Scanner scanner = new Scanner(source, StandardCharsets.UTF_8.name())) {
            if (skipColumnTitle) {
                scanner.nextLine();
            }
            while (scanner.hasNextLine()) {
                rows.add(getRecordFromLine(scanner.nextLine()));
            }
        }
        return rows;
    }

    private static List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }
}
