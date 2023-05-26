package com.eptexcoatings.assignment.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Pouya Khoshkhou
 * @since 07/07/2023
 */
@Data
public class GetCsvRecordsResponseDto implements Serializable {

    private List<GetCsvRecordResponseDto> getCsvRecordResponseDtoList;
}
