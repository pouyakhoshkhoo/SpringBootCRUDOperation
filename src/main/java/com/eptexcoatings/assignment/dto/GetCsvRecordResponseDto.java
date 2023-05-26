package com.eptexcoatings.assignment.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Pouya Khoshkhou
 * @since 07/07/2023
 */
@Data
public class GetCsvRecordResponseDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 6760714231497175308L;

    private String source;
    private String codeListCode;
    private String code;
    private String displayValue;
    private String longDescription;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Integer sortingPriority;
}
