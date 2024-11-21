package com.university.createTest;

import static org.junit.jupiter.api.Assertions.*;

import com.university.create.CreateEvaluation;
import com.university.University;
import com.university.classroom.Student;
import com.university.evaluation.Evaluation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class EvaluationTest {
private List<Evaluation> evaluations;
    private CreateEvaluation evaluationCreator;
    private University university;

    @BeforeEach
    void setUp() {
        evaluations = new ArrayList<>();
        evaluationCreator = new CreateEvaluation(evaluations);
        university = new University();
        Student student = new Student("John Doe","john@doe.com");
        Student student1 = new Student("Alice Azure","alice@gmail.com");
        Student student2 = new Student("Paul Beige","paul@gmail.com");
        Student student3 = new Student("Olivia Red","olivi@gmail.com");
        university.addStudent(student);
        university.addStudent(student1);
        university.addStudent(student2);
        university.addStudent(student3);
    }

    @Test
    void testNewEvaluation() {
        String parts = "John Doe, Programacion, WRITTEN_EXAM, Parcial, , 8.5";
        Evaluation evaluation = evaluationCreator.getOrCreate(parts, evaluations, university);
        assertEquals(1, university.getEvaluations().size());
        assertEquals("Programacion", evaluation.getSubjectName());
        assertEquals("Parcial", evaluation.getName());
        assertEquals("John Doe", evaluation.getStudentName());
        assertEquals("WRITTEN_EXAM", evaluation.getEvaluationType());

        Student student = university.getStudents().getFirst();
        assertTrue(student.getEvaluations().contains(evaluation));
    }

    @Test
    void testExistingEvaluation() {
        String parts = "John Doe, Programacion, WRITTEN_EXAM, Parcial, , 8.0";
        Evaluation initialEvaluation = evaluationCreator.getOrCreate(parts, evaluations, university);
        String newParts = "John Doe, Programacion, WRITTEN_EXAM, Parcial, , 7.0";
        Evaluation fetchedEvaluation = evaluationCreator.getOrCreate(newParts, evaluations, university);
        assertEquals(1, university.getEvaluations().size());
        assertEquals(initialEvaluation, fetchedEvaluation);
        assertEquals("Programacion", fetchedEvaluation.getSubjectName());
        assertEquals("Parcial", fetchedEvaluation.getName());
        assertEquals("John Doe", fetchedEvaluation.getStudentName());
    }

    @Test
    void testMultipleEvaluationCreation(){
        String parts1 = "John Doe, Economics, WRITTEN_EXAM, Primer Parcial, , 8.0";
        String parts2 = "Alice Azure, Math, ORAL_EXAM, Oral Exam, , 8.0";
        String parts3 = "Paul Beige, History, FINAL_PRACTICAL_WORK, Final, , 8.0";
        String parts4 = "Olivia Red, Arts, PRACTICAL_WORK, Tp2, , 8.0";
        Evaluation evaluation1 = evaluationCreator.getOrCreate(parts1,evaluations,university);
        Evaluation evaluation2 = evaluationCreator.getOrCreate(parts2,evaluations,university);
        Evaluation evaluation3 = evaluationCreator.getOrCreate(parts3,evaluations,university);
        Evaluation evaluation4 = evaluationCreator.getOrCreate(parts4,evaluations,university);
        assertEquals("WRITTEN_EXAM", evaluation1.getEvaluationType());
        assertEquals("ORAL_EXAM", evaluation2.getEvaluationType());
        assertEquals("FINAL_PRACTICAL_WORK", evaluation3.getEvaluationType());
        assertEquals("PRACTICAL_WORK", evaluation4.getEvaluationType());
    }

    @Test
    void testStudentNotFound() {
        String parts = "Jane Doe, Algebra, ORAL_EXAM, Examen Oral, , 9.0";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            evaluationCreator.getOrCreate(parts, evaluations, university);
        });
        assertEquals("Student not found in university: Jane Doe", exception.getMessage());
    }

    @Test
    void testInvalidType() {
        String invalidParts = "John Doe, Algebra, UNKNOWN_TYPE, Parcial, , 6.0";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            evaluationCreator.getOrCreate(invalidParts, evaluations, university);
        });
        assertEquals("Unknown evaluation type: UNKNOWN_TYPE", exception.getMessage());
    }
}