package com.university.readersTest;

import com.university.readers.WriterC;
import com.university.University;
import com.university.classroom.Course;
import com.university.classroom.Student;
import com.university.evaluation.Evaluation;
import com.university.evaluation.FinalExam;
import com.university.evaluation.criteria.Criteria;
import com.university.evaluation.criteria.Criterion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WriterCTest {
    private University university;
    private Criteria criteriaProcessor;
    private WriterC writer3;

    @BeforeEach
    void setUp() {
        university = new University();
        Student student = new Student("John Doe","johndoe@gmail.com");
        Student student1 = new Student("Jane Smith","janesmith@gmail.com");
        university.addStudent(student);
        university.addStudent(student1);
        Course math = new Course(505,"Math");
        Course history = new Course(132,"History");
        university.addCourse(math);
        university.addCourse(history);
        Evaluation mathExam = new FinalExam("Final", "John Doe", "Math", "FINAL_EXAM");
        mathExam.addGrades(9.0);
        student.addEvaluation(mathExam);
        Evaluation historyExam = new FinalExam("Primer Parcial", "Jane Smith", "History", "FINAL_EXAM");
        historyExam.addGrades(6.0);
        student1.addEvaluation(historyExam);
        criteriaProcessor = new Criteria();
        String evaluationName1 = "Examen Final";
        String evaluationName2 = "Examen Final";
        List<String> evaluationNames1 = new ArrayList<>();
        List<String> evaluationNames2 = new ArrayList<>();
        evaluationNames1.add(evaluationName1);
        evaluationNames2.add(evaluationName2);
        criteriaProcessor.addCriteria(new Criterion("Math", "MAX_ABOVE_VALUE",4.0, evaluationNames1));
        criteriaProcessor.addCriteria(new Criterion("History", "MAX_ABOVE_VALUE",4.0, evaluationNames2));
        writer3 = new WriterC();
    }

    @Test
    void testWrite() throws IOException {
        StringWriter stringWriter = new StringWriter();
        BufferedWriter bufferedWriter = new BufferedWriter(stringWriter);
        writer3.write(university, criteriaProcessor, bufferedWriter);
        bufferedWriter.flush();
        String expectedOutput =
                "Student,Subject,Status\n" +
                        "John Doe,Math,FAILED\n" +
                        "Jane Smith,History,FAILED\n";
        assertEquals(expectedOutput, stringWriter.toString(), "La salida generada debe coincidir con el formato esperado.");
    }
}
