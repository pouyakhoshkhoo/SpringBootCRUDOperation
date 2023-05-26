package com.eptexcoatings.assignment.service.impl;

import com.eptexcoatings.assignment.entity.CsvRecord;
import com.eptexcoatings.assignment.service.CsvFileParser;
import com.opencsv.exceptions.CsvException;
import com.eptexcoatings.assignment.assembler.CsvDtoAssembler;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

class CsvFileParserImplTest {

    private CsvFileParser csvFileParser = new CsvFileParserImpl(new CsvDtoAssembler());


    @Test
    void parseCsvFile() throws IOException, CsvException {
        MultipartFile csvFile = getMultipartFile();
        List<String[]> csvData = csvFileParser.parseCsvFile(csvFile);
        Assertions.assertThat(csvData).size().isEqualTo(19);
    }

    @Test
    void convertToCsvRecords() throws IOException, CsvException {
        MultipartFile csvFile = getMultipartFile();
        List<String[]> csvData = csvFileParser.parseCsvFile(csvFile);
        List<CsvRecord> csvRecords = csvFileParser.convertToCsvRecords(csvData);
    }

    private MultipartFile getMultipartFile() throws IOException {
        ClassPathResource resource = new ClassPathResource("sample/exercise.csv");
        MultipartFile csvFile = new MockMultipartFile("file",
                resource.getFile().getName(), "text/plain", resource.getInputStream());
        return csvFile;
    }
}