package com.university.evaluationTest.criteriaTest;

import com.university.University;
import com.university.classroom.Student;
import com.university.evaluation.FinalExam;
import com.university.evaluation.WrittenExam;
import com.university.evaluation.criteria.Criteria;
import com.university.evaluation.criteria.Criterion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CriteriaTest {
    private Criteria criteriaProcessor;
    private University university;
    private Student student1;
    private Student student2;
    private FinalExam finalExam;
    private WrittenExam writtenExam;

    @BeforeEach
    void setUp() {
        criteriaProcessor = new Criteria();
        student1 = new Student("Alice","alice@gmail.com");
        student2 = new Student("Bob","bob@email.com");
        finalExam = new FinalExam("Final", "Alice", "Matemáticas", "FINAL_EXAM");
        writtenExam = new WrittenExam("Examen 1", "Bob", "Física", "WRITTEN_EXAM");
        finalExam.addGrades(6.0);
        finalExam.addGrades(4.0);
        writtenExam.addGrades(7.0);
        writtenExam.addGrades(5.0);
        student1.addEvaluation(finalExam);
        student2.addEvaluation(writtenExam);
        Criterion criterionMatemat = new Criterion("Matemáticas", "AVERAGE_ABOVE_VALUE", 5.5, Arrays.asList("Final"));
        Criterion criterionFisica = new Criterion("Física", "MAX_ABOVE_VALUE", 6.0, Arrays.asList("Examen 1"));
        criteriaProcessor.addCriteria(criterionMatemat);
        criteriaProcessor.addCriteria(criterionFisica);
        university = new University();
        university.addStudent(student1);
        university.addStudent(student2);
    }

    @Test
    void testAddCriteria() {
        List<Criterion> criteria = criteriaProcessor.getCriteriaList();
        assertEquals(2, criteria.size(), "There should be 2 criteria");
    }

    @Test
    void testPassed() {
        List<String> results = criteriaProcessor.evaluateStudents(university);
        assertTrue(results.contains("Bob,Física,PASSED"));
    }

    @Test
    void testFailed() {
        finalExam.addGrades(1.0);
        student1.addEvaluation(finalExam);
        List<String> results = criteriaProcessor.evaluateStudents(university);
        assertTrue(results.contains("Alice,Matemáticas,FAILED"));
    }

    @Test
    void testAverageAboveValue() {
        Criterion averageCriterion = new Criterion("Matemáticas", "AVERAGE_ABOVE_VALUE", 5.0, Arrays.asList("Final"));
        assertTrue(averageCriterion.evaluate(student1));
        student1.addEvaluation(finalExam);
        assertTrue(averageCriterion.evaluate(student1));
    }

    @Test
    void testMaxAboveValue() {
        Criterion maxCriterion = new Criterion("Física", "MAX_ABOVE_VALUE", 6.0, Arrays.asList("Examen 1"));
        assertTrue(maxCriterion.evaluate(student2));
    }

    @Test
    void testEvaluateWithMinAboveValue() {
        Criterion minCriterion = new Criterion("Matemáticas", "MIN_ABOVE_VALUE", 4.0, Arrays.asList("Final"));
        assertTrue(minCriterion.evaluate(student1));
    }
}
