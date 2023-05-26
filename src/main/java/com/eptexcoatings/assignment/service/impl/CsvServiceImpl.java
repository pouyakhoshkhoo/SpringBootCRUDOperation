package com.eptexcoatings.assignment.service.impl;

import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.eptexcoatings.assignment.assembler.CsvDtoAssembler;
import com.eptexcoatings.assignment.dto.GetCsvRecordResponseDto;
import com.eptexcoatings.assignment.dto.GetCsvRecordsResponseDto;
import com.eptexcoatings.assignment.entity.CsvRecord;
import com.eptexcoatings.assignment.entity.FileName;
import com.eptexcoatings.assignment.exception.AssignmentInternalException;
import com.eptexcoatings.assignment.exception.AssignmentInternalRuntimeException;
import com.eptexcoatings.assignment.exception.InvalidFileFormatException;
import com.eptexcoatings.assignment.exception.RecordNotFoundException;
import com.eptexcoatings.assignment.repository.CsvRecordRepository;
import com.eptexcoatings.assignment.repository.FileNameRepository;
import com.eptexcoatings.assignment.service.CsvFileParser;
import com.eptexcoatings.assignment.service.CsvService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Pouya Khoshkhou
 * @since 07/07/2023
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class CsvServiceImpl implements CsvService {

    private final CsvRecordRepository repository;
    private final CsvFileParser csvFileParser;
    private final CsvDtoAssembler csvDtoAssembler;

    private final FileNameRepository fileNameRepository;

    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    @Override
    public void processCsvFile(MultipartFile file) {
        try {
            List<String[]> records = csvFileParser.parseCsvFile(file);
            csvFileParser.validateCsvRecords(records);
            FileName entity = new FileName();
            entity.setName("test");
            FileName save = fileNameRepository.save(entity);
            List<CsvRecord> csvRecords = csvFileParser.convertToCsvRecords(records);

            save.setRecords(repository.saveAll(csvRecords).stream().collect(Collectors.toSet()));
        } catch (RecordNotFoundException e) {
            log.warn("Unexpected exception occurred during saving file", e);
            throw e;
        } catch (InvalidFileFormatException e) {
            log.warn("Csv file is not valid", e);
            throw e;
        } catch (CsvException e) {
            log.warn("Unexpected exception occurred during saving file", e);
            throw new AssignmentInternalRuntimeException("Internal Exception occured", e);
        } catch (IOException e) {
            log.warn("Unexpected exception occurred during saving file", e);
            throw new AssignmentInternalRuntimeException("Internal Exception occured", e);
        }
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    @Override
    public GetCsvRecordsResponseDto getAllCsvRecords() throws AssignmentInternalException, RecordNotFoundException {
        try {
            return csvDtoAssembler.makeCsvRecordsResponseDto(repository.findAll());
        } catch (RecordNotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.warn("Unexpected exception occurred", e);
            throw new AssignmentInternalException("Internal Exception occured", e);
        }
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    @Override
    public GetCsvRecordResponseDto getCsvRecordByCode(String code) throws RecordNotFoundException, AssignmentInternalException {
        try {
            Optional<CsvRecord> csvRecordOptional = repository.findCsvRecordByCode(code);
            if (csvRecordOptional.isEmpty()) {
                throw new RecordNotFoundException();
            }
            return csvDtoAssembler.makeGetCsvRecordResponseDto(csvRecordOptional.get());
        } catch (RecordNotFoundException e) {
            log.info("Record not found for code: {}", code);
            throw e;
        } catch (Exception e) {
            log.warn("Unexpected exception occurred", e);
            throw new AssignmentInternalException("Internal Exception occured", e);
        }
    }

    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    @Override
    public void deleteAllCsvRecords() {
        try {
            repository.deleteAll();
        } catch (Exception e) {
            log.warn("Unexpected exception occurred during delete all records", e);
            throw new AssignmentInternalRuntimeException("Internal Exception occured", e);
        }
    }
}
