package com.university.readersTest;

import com.university.readers.WriterB;
import com.university.University;
import com.university.evaluation.FinalExam;
import com.university.evaluation.WrittenExam;
import com.university.evaluation.criteria.Criteria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;

public class WriterBTest {
    private University university;
    private Criteria criteriaProcessor;
    private WriterB writer2;

    @BeforeEach
    void setUp() {
        university = new University();
        criteriaProcessor = new Criteria();
        writer2 = new WriterB();
    }

    @Test
    void testWrite() throws IOException {

        FinalExam finalExam = new  FinalExam("Final", "John Doe", "Math", "FinalExam");
        finalExam.addGrades(9);
        WrittenExam writtenExam = new  WrittenExam("Primer Parcial", "Jane Doe", "History", "WrittenExam");
        writtenExam.addGrades(6);
        university.addEvaluation(finalExam);
        university.addEvaluation(writtenExam);
        StringWriter stringWriter = new StringWriter();
        BufferedWriter bufferedWriter = new BufferedWriter(stringWriter);
        writer2.write(university, criteriaProcessor, bufferedWriter);
        bufferedWriter.flush();
        String expectedOutput =
                "Subject_Name,Evaluation_Name,Student_Name,Grade\n" +
                        "History,Primer Parcial,Jane Doe,6.0\n" +
                        "Math,Final,John Doe,9.0";
        String normalizedExpectedOutput = expectedOutput.replaceAll("\\s+", "").replace("\r\n", "\n").replace("\r", "\n");
        String normalizedActualOutput = stringWriter.toString().replaceAll("\\s+", "").replace("\r\n", "\n").replace("\r", "\n");

        assertEquals(normalizedExpectedOutput, normalizedActualOutput);

    }
}
