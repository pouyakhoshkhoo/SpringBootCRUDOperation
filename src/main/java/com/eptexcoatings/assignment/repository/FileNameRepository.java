package com.eptexcoatings.assignment.repository;

import com.eptexcoatings.assignment.entity.FileName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Pouya Khoshkhou
 * @since 07/07/2023
 */
@Repository
public interface FileNameRepository extends JpaRepository<FileName, Long> {

}
