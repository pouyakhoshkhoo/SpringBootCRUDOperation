package com.eptexcoatings.assignment.service;

import com.eptexcoatings.assignment.dto.GetCsvRecordResponseDto;
import com.eptexcoatings.assignment.dto.GetCsvRecordsResponseDto;
import com.eptexcoatings.assignment.exception.AssignmentInternalException;
import com.eptexcoatings.assignment.exception.AssignmentInternalRuntimeException;
import com.eptexcoatings.assignment.exception.RecordNotFoundException;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Pouya Khoshkhou
 * @since 07/07/2023
 */
public interface CsvService {

    void processCsvFile(MultipartFile file);

    GetCsvRecordsResponseDto getAllCsvRecords() throws AssignmentInternalException, RecordNotFoundException;

    GetCsvRecordResponseDto getCsvRecordByCode(String code) throws RecordNotFoundException, AssignmentInternalException;

    void deleteAllCsvRecords() throws AssignmentInternalRuntimeException;
}
