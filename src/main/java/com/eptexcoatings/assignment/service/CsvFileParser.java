package com.eptexcoatings.assignment.service;

import com.eptexcoatings.assignment.entity.CsvRecord;
import com.opencsv.exceptions.CsvException;
import com.eptexcoatings.assignment.exception.InvalidFileFormatException;
import com.eptexcoatings.assignment.exception.RecordNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author Pouya Khoshkhou
 * @since 07/07/2023
 */
public interface CsvFileParser {

    List<String[]> parseCsvFile(MultipartFile file) throws IOException, CsvException;

    void validateCsvRecords(List<String[]> records) throws RecordNotFoundException, InvalidFileFormatException;

    List<CsvRecord> convertToCsvRecords(List<String[]> records) throws RecordNotFoundException;
}
