package com.eptexcoatings.assignment.service.impl;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import com.eptexcoatings.assignment.assembler.CsvDtoAssembler;
import com.eptexcoatings.assignment.entity.CsvRecord;
import com.eptexcoatings.assignment.exception.InvalidFileFormatException;
import com.eptexcoatings.assignment.exception.RecordNotFoundException;
import com.eptexcoatings.assignment.service.CsvFileParser;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Pouya Khoshkhou
 * @since 07/07/2023
 */
@RequiredArgsConstructor
@Service
public class CsvFileParserImpl implements CsvFileParser {

    private final CsvDtoAssembler csvDtoAssembler;

    @Override
    public List<String[]> parseCsvFile(MultipartFile file) throws IOException, CsvException {
        try (Reader reader = new InputStreamReader(file.getInputStream())) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                return csvReader.readAll();
            }
        }
    }

    @Override
    public void validateCsvRecords(List<String[]> records) throws InvalidFileFormatException {
        for (String[] x : records) {
            if (x.length != 8) {
                throw new InvalidFileFormatException("Invalid File format");
            }
        }
    }

    @Override
    public List<CsvRecord> convertToCsvRecords(List<String[]> records) throws RecordNotFoundException {
        if (CollectionUtils.isEmpty(records) || records.size() <= 1) {
            throw new RecordNotFoundException("No record");
        }

        // remove header row before converting
        records.remove(0);

        return records.stream()
                .map(csvDtoAssembler::makeCsvRecord)
                .collect(Collectors.toList());
    }
}
