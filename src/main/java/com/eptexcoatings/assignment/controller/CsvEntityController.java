package com.eptexcoatings.assignment.controller;

import com.eptexcoatings.assignment.dto.GetCsvRecordResponseDto;
import com.eptexcoatings.assignment.dto.GetCsvRecordsResponseDto;
import com.eptexcoatings.assignment.exception.AssignmentInternalRuntimeException;
import com.eptexcoatings.assignment.exception.AssignmentInternalException;
import com.eptexcoatings.assignment.exception.RecordNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Pouya Khoshkhou
 * @since 07/07/2023
 */
@RequestMapping("/csv")
public interface CsvEntityController {

    @PostMapping("/")
    void uploadCsvFile(@RequestParam("file") MultipartFile file) throws AssignmentInternalRuntimeException;

    @GetMapping("/")
    GetCsvRecordsResponseDto getAllCsvRecords() throws AssignmentInternalException, RecordNotFoundException;

    @GetMapping("/{code}")
    GetCsvRecordResponseDto getCsvRecordByCode(@PathVariable String code) throws AssignmentInternalException, RecordNotFoundException;

    @DeleteMapping("/")
    void deleteAllCsvRecords() throws AssignmentInternalRuntimeException;

}
