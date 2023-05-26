package com.eptexcoatings.assignment.assembler;

import com.eptexcoatings.assignment.entity.CsvRecord;
import lombok.extern.slf4j.Slf4j;
import com.eptexcoatings.assignment.dto.GetCsvRecordResponseDto;
import com.eptexcoatings.assignment.dto.GetCsvRecordsResponseDto;
import com.eptexcoatings.assignment.exception.InvalidFileFormatException;
import com.eptexcoatings.assignment.exception.RecordNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Pouya Khoshkhou
 * @since 07/07/2023
 */
@Service
@Slf4j
public class CsvDtoAssembler {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public GetCsvRecordsResponseDto makeCsvRecordsResponseDto(List<CsvRecord> csvRecords) throws RecordNotFoundException {
        if (CollectionUtils.isEmpty(csvRecords)) {
            throw new RecordNotFoundException("Record not found");
        }
        List<GetCsvRecordResponseDto> collect = csvRecords
                .stream()
                .map(this::makeGetCsvRecordResponseDto)
                .collect(Collectors.toList());
        GetCsvRecordsResponseDto getCsvRecordsResponseDto = new GetCsvRecordsResponseDto();
        getCsvRecordsResponseDto.setGetCsvRecordResponseDtoList(collect);
        return getCsvRecordsResponseDto;
    }

    public GetCsvRecordResponseDto makeGetCsvRecordResponseDto(CsvRecord csvRecord) {
        GetCsvRecordResponseDto getCsvRecordResponseDto = new GetCsvRecordResponseDto();
        getCsvRecordResponseDto.setCode(csvRecord.getCode());
        getCsvRecordResponseDto.setCodeListCode(csvRecord.getCodeListCode());
        getCsvRecordResponseDto.setDisplayValue(csvRecord.getDisplayValue());
        getCsvRecordResponseDto.setFromDate(csvRecord.getFromDate());
        getCsvRecordResponseDto.setToDate(csvRecord.getToDate());
        getCsvRecordResponseDto.setSource(csvRecord.getSource());
        getCsvRecordResponseDto.setLongDescription(csvRecord.getLongDescription());
        getCsvRecordResponseDto.setSortingPriority(csvRecord.getSortingPriority());
        return getCsvRecordResponseDto;
    }

    public CsvRecord makeCsvRecord(String[] fields) {
        try {
            CsvRecord csvRecord = new CsvRecord();
            csvRecord.setSource(fields[0]);
            csvRecord.setCodeListCode(fields[1]);
            csvRecord.setCode(fields[2]);
            csvRecord.setDisplayValue(fields[3]);
            csvRecord.setLongDescription(fields[4]);
            csvRecord.setFromDate(StringUtils.isNotBlank(fields[5]) ? LocalDate.parse(fields[5], formatter) : null);
            csvRecord.setToDate(StringUtils.isNotBlank(fields[6]) ? LocalDate.parse(fields[6], formatter) : null);
            csvRecord.setSortingPriority(StringUtils.isNotBlank(fields[7]) ? Integer.parseInt(fields[7]) : null);
            return csvRecord;
        } catch (Exception e) {
            log.info("{}", Arrays.stream(fields).reduce(String::concat));
            throw new InvalidFileFormatException("Csv file is not valid");
        }
    }
}