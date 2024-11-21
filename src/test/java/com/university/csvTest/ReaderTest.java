package com.university.csvTest;

import com.university.readers.Creators;
import com.university.readers.CreatorA;
import com.university.readers.CreatorB;
import com.university.University;
import com.university.csv.Reader;
import com.university.evaluation.Evaluation;
import com.university.evaluation.criteria.Criteria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReaderTest {
    private String filePath;

    @BeforeEach
    public void setUp() {
        filePath = "test.csv";
    }
    @Test
    public void testEmptyFile() throws IOException {
        String csvContent = "";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(csvContent);
        }
        University university = new University();
        Creators creator = new CreatorA();
        Criteria criteriaProcessor = new Criteria();
        Reader.processCSV(filePath, university, creator, criteriaProcessor);
        List<Evaluation> evaluations = university.getEvaluations();
        assertEquals(0, evaluations.size());
    }

    @Test
    public void testFileNotFound() {
        String invalidFilePath = "invalid.csv";
        University university = new University();
        Creators creator = new CreatorB();
        Criteria criteriaProcessor = new Criteria();
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            Reader.processCSV(invalidFilePath, university, creator, criteriaProcessor)
        );
        assertEquals("The specified file was not found: " + invalidFilePath, exception.getMessage());
    }
}
