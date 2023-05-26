package com.eptexcoatings.assignment.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author Pouya Khoshkhou
 * @since 07/07/2023
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "CSV_RECORD", indexes = {@Index(name = "IDX_CODE_1", columnList = "CODE")})
@SequenceGenerator(name = "csvRecordSeq", sequenceName = "SEQ_CSV_RECORD", allocationSize = 1)
public class CsvRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "csvRecordSeq")
    @Column(name = "ID")
    private Long id;

    @Column(name = "SOURCE", nullable = false)
    private String source;

    @Column(name = "CODE_LIST_CODE", nullable = false)
    private String codeListCode;

    @Column(name = "CODE", nullable = false)
    private String code;

    @Column(name = "DISPLAY_VALUE")
    private String displayValue;

    @Column(name = "LONG_DESCRIPTION")
    private String longDescription;

    @Column(name = "FROM_DATE", columnDefinition = "TIMESTAMP")
    private LocalDate fromDate;

    @Column(name = "TO_DATE", columnDefinition = "TIMESTAMP")
    private LocalDate toDate;

    @Column(name = "SORTING_PRIORITY")
    private Integer sortingPriority;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CsvRecord csvRecord = (CsvRecord) o;

        if (!id.equals(csvRecord.id)) return false;
        if (!source.equals(csvRecord.source)) return false;
        if (!codeListCode.equals(csvRecord.codeListCode)) return false;
        return code.equals(csvRecord.code);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
