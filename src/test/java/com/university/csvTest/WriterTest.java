package com.university.csvTest;

import com.university.readers.WriterA;
import com.university.University;
import com.university.csv.Writer;
import com.university.evaluation.OralExam;
import com.university.evaluation.WrittenExam;
import com.university.evaluation.criteria.Criteria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class WriterTest {
    private String filePath;

    @BeforeEach
    public void setUp() {
        filePath = "output.csv";
    }

    @Test
    public void testWriteCSVHandlesEmptyUniversity() throws IOException {
        University university = new University();
        Criteria criteriaProcessor = new Criteria();
        Writer.writeCSV(filePath, university, criteriaProcessor, new WriterA());
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        assertEquals(1, lines.size());
        Files.delete(Paths.get(filePath));
    }

    @Test
    public void testWriteCSVHandlesIOException() {
        String invalidFilePath = "/invalid_directory/output.csv";
        University university = new University();
        Criteria criteriaProcessor = new Criteria();
        try {
            Writer.writeCSV(invalidFilePath, university, criteriaProcessor, new WriterA());
            fail("Expected IOException was not thrown");
        } catch (IOException e) {
            assertNotNull(e.getMessage());
        }
    }
}
