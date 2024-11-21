package com.university.evaluationTest.criteriaTest;

import com.university.classroom.Student;
import com.university.evaluation.*;
import com.university.evaluation.criteria.Criterion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class CriterionTest {
    private Student student;
    private Criterion averageCriterion;
    private Criterion maxCriterion;
    private Criterion minCriterion;

    @BeforeEach
    public void setUp() {
        student = new Student("Paul Beige", "paul.beige@example.com");
        List<String> evaluationsTest = new ArrayList<>();
        WrittenExam writtenExam1 = new WrittenExam("Examen 1", "Paul Beige", "English", "WRITTEN_EXAM");
        writtenExam1.addGrades(7.0);
        writtenExam1.addGrades(5.0);
        writtenExam1.addGrades(8.0);
        PracticalWork practicalWork = new PracticalWork("Trabajo Práctico 1", "Paul Beige", "Computer Science", "FINAL_PRACTICAL_WORK");
        practicalWork.addGrades(6.0);
        practicalWork.addGrades(5.0);
        OralExam oralExam = new OralExam("Examen Oral 1", "Paul Beige", "Sociology", "ORAL_EXAM");
        oralExam.addGrades(8.0); // Examen oral
        student.addEvaluation(writtenExam1);
        student.addEvaluation(practicalWork);
        student.addEvaluation(oralExam);
        evaluationsTest.add(writtenExam1.getName());
        evaluationsTest.add(practicalWork.getName());
        evaluationsTest.add(oralExam.getName());
        averageCriterion = new Criterion("English", "AVERAGE_ABOVE_VALUE", 6.0, evaluationsTest);
        maxCriterion = new Criterion("Computer Science", "MAX_ABOVE_VALUE", 6.0, evaluationsTest);
        minCriterion = new Criterion("Sociology", "MIN_ABOVE_VALUE", 7.0, evaluationsTest);
    }

    @Test
    public void testEvaluateAverageAboveValue() {
        assertTrue(averageCriterion.evaluate(student));
    }

    @Test
    public void testEvaluateMaxAboveValue() {
        assertTrue(maxCriterion.evaluate(student));
    }

    @Test
    public void testEvaluateMinAboveValue() {
        assertTrue(minCriterion.evaluate(student), "El estudiante debería cumplir con el criterio mínimo para el examen oral.");
    }
}
