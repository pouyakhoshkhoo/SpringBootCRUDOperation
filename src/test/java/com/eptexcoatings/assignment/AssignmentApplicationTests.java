package com.eptexcoatings.assignment;

import com.eptexcoatings.assignment.entity.CsvRecord;
import com.eptexcoatings.assignment.repository.CsvRecordRepository;
import com.eptexcoatings.assignment.service.CsvService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@SpringBootTest
class AssignmentApplicationTests {


    @Autowired
    private CsvRecordRepository csvRecordRepository;
    @Autowired
    private CsvService csvService;

    @Test
    void testUploadFile() throws IOException {
        csvService.processCsvFile(getMultipartFile());
        List<CsvRecord> all = csvRecordRepository.findAll();
        Assertions.assertThat(all).size().isEqualTo(18);
    }

    private MultipartFile getMultipartFile() throws IOException {
        ClassPathResource resource = new ClassPathResource("sample/exercise.csv");
        MultipartFile csvFile = new MockMultipartFile("file",
                resource.getFile().getName(), "text/plain", resource.getInputStream());
        return csvFile;
    }
}
