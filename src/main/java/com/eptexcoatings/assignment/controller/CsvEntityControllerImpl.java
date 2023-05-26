package com.eptexcoatings.assignment.controller;

import com.eptexcoatings.assignment.dto.GetCsvRecordResponseDto;
import com.eptexcoatings.assignment.dto.GetCsvRecordsResponseDto;
import com.eptexcoatings.assignment.exception.AssignmentInternalRuntimeException;
import com.eptexcoatings.assignment.service.CsvService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.eptexcoatings.assignment.exception.AssignmentInternalException;
import com.eptexcoatings.assignment.exception.InvalidFileFormatException;
import com.eptexcoatings.assignment.exception.RecordNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Pouya Khoshkhou
 * @since 07/07/2023
 */
@Tag(name = "CSV entity controller")
@RestController
@RequiredArgsConstructor
@Slf4j
public class CsvEntityControllerImpl implements CsvEntityController {

    private final CsvService csvService;

    @Operation(summary = "Uploading csv file")
    @ApiResponse(responseCode = "200", description = "File saved successfully.")
    @ApiResponse(responseCode = "400", description = "Bad csv file", content = @Content(schema = @Schema(implementation =
            InvalidFileFormatException.class)))
    @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content(schema = @Schema(implementation =
            AssignmentInternalRuntimeException.class)))
    @Override
    public void uploadCsvFile(@RequestParam("file") MultipartFile file) throws AssignmentInternalRuntimeException {
        csvService.processCsvFile(file);
    }

    @Operation(summary = "Get all CSV records")
    @ApiResponse(responseCode = "200", description = "Records founded successfully", content = @Content(schema = @Schema(implementation =
            GetCsvRecordsResponseDto.class)))
    @ApiResponse(responseCode = "404", description = "Records not found", content = @Content(schema = @Schema(implementation =
            RecordNotFoundException.class)))
    @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content(schema = @Schema(implementation =
            AssignmentInternalException.class)))
    @Override
    public @ResponseBody GetCsvRecordsResponseDto getAllCsvRecords() throws AssignmentInternalException, RecordNotFoundException {
        return csvService.getAllCsvRecords();
    }

    @Operation(summary = "Get scv record by code")
    @ApiResponse(responseCode = "200", description = "Record found successfully", content = @Content(schema = @Schema(implementation =
            GetCsvRecordResponseDto.class)))
    @ApiResponse(responseCode = "404", description = "Record not found with this code", content = @Content(schema = @Schema(implementation =
            RecordNotFoundException.class)))
    @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content(schema = @Schema(implementation =
            AssignmentInternalException.class)))
    @Override
    public @ResponseBody GetCsvRecordResponseDto getCsvRecordByCode(@PathVariable String code)
            throws AssignmentInternalException, RecordNotFoundException {
        return csvService.getCsvRecordByCode(code);
    }

    @Operation(summary = "Delete all csv records")
    @ApiResponse(responseCode = "200", description = "All records has been deleted successfully")
    @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content(schema = @Schema(implementation =
            AssignmentInternalRuntimeException.class)))
    @Override
    public void deleteAllCsvRecords() throws AssignmentInternalRuntimeException {
        csvService.deleteAllCsvRecords();
    }
}
