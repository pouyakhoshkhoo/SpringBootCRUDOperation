package com.eptexcoatings.assignment.repository;

import com.eptexcoatings.assignment.entity.CsvRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Pouya Khoshkhou
 * @since 07/07/2023
 */
@Repository
public interface CsvRecordRepository extends JpaRepository<CsvRecord, Long> {

    Optional<CsvRecord> findCsvRecordByCode(String code);
}
