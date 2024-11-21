package com.university.evaluationTest;

import com.university.evaluation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class EvaluationTest {
    private FinalExam finalExam;
    private OralExam oralExam;
    private PracticalWork practicalWork;
    private WrittenExam writtenExam;

    @BeforeEach
    void setUp() {
        finalExam = new FinalExam("Final", "Alice", "Matemáticas", "FINAL_EXAM");
        oralExam = new OralExam("Oral 1", "Bob", "Historia", "ORAL_EXAM");
        practicalWork = new PracticalWork("TP1", "Charlie", "Química", "PRACTICAL_WORK");
        writtenExam = new WrittenExam("Examen 1", "David", "Física", "WRITTEN_EXAM");
        finalExam.addGrades(5.0);
        finalExam.addGrades(2.0);
        oralExam.addGrades(7.0);
        practicalWork.addGrades(3.0);
        practicalWork.addGrades(4.0);
        writtenExam.addGrades(7.0);
        writtenExam.addGrades(5.0);
    }

    @Test
    void testAllEvaluations() {
        assertEquals(7.0, finalExam.getGrade());
        assertEquals("Alice", finalExam.getStudentName());
        assertEquals("FINAL_EXAM", finalExam.getEvaluationType());
        assertEquals(7.0, oralExam.getGrade());
        assertEquals("Bob", oralExam.getStudentName());
        assertEquals("ORAL_EXAM", oralExam.getEvaluationType());
        assertEquals(4.0, practicalWork.getGrade());
        assertEquals("Charlie", practicalWork.getStudentName());
        assertEquals("PRACTICAL_WORK", practicalWork.getEvaluationType());
        assertEquals(6.0, writtenExam.getGrade());
        assertEquals("David", writtenExam.getStudentName());
        assertEquals("WRITTEN_EXAM", writtenExam.getEvaluationType());

        int originalId = finalExam.getId();
        finalExam.setId(2);
        assertEquals(2, finalExam.getId());
        finalExam.setId(originalId);
        assertEquals("Final", finalExam.getName());
        finalExam.setName("Parcial");
        assertEquals("Parcial", finalExam.getName());
        oralExam.setStudentName("John");
        assertEquals("John", oralExam.getStudentName());
        practicalWork.setSubjectName("Fisica");
        assertEquals("Fisica", practicalWork.getSubjectName());
        oralExam.setEvaluationType("ORAL_EXAM");
        assertEquals("ORAL_EXAM", oralExam.getEvaluationType());
        String expected = "Evaluation{id=" + finalExam.getId() + ", name='Parcial', studentName='Alice', subjectName='Matemáticas', evaluationType='FINAL_EXAM', grades=[5.0, 2.0]}";
        assertEquals(expected, finalExam.toString());
    }

    @Test
    void testAddGradesAndGetGrades() {
        finalExam.addGrades(10.0);
        assertEquals(Arrays.asList(5.0, 2.0, 10.0), finalExam.getGrades());
    }

    @Test
    void testGetStudentName() {
        assertEquals("Alice", finalExam.getStudentName());
        assertEquals("Bob", oralExam.getStudentName());
    }

    @Test
    void testGetEvaluationType() {
        assertEquals("FINAL_EXAM", finalExam.getEvaluationType());
        assertEquals("ORAL_EXAM", oralExam.getEvaluationType());
    }
}
