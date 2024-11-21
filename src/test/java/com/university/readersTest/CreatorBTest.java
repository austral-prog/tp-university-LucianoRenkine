package com.university.readersTest;

import com.university.readers.CreatorB;
import com.university.University;
import com.university.classroom.Student;
import com.university.evaluation.Evaluation;
import com.university.evaluation.criteria.Criteria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CreatorBTest {
    private University university;
    private CreatorB creator2;
    private Criteria criteriaProcessor;

    @BeforeEach
    void setUp() {
        university = new University();
        creator2 = new CreatorB();
        criteriaProcessor = new Criteria();
    }

    @Test
    void testCreate() {
        Student student = new Student("Paul Beige", "paulbaige@gmail.com");
        university.addStudent(student);
        String parts = "Paul Beige,English,WRITTEN_EXAM,Segundo Parcial,Ej2,7";
        creator2.create(parts, university, criteriaProcessor);
        Evaluation evaluation = university.getEvaluations().getFirst();
        assertNotNull(evaluation);
        assertEquals("Paul Beige", evaluation.getStudentName());
        assertEquals("English", evaluation.getSubjectName());
        assertEquals(7, evaluation.getGrade());
        assertEquals("WRITTEN_EXAM", evaluation.getEvaluationType());
        assertEquals("Segundo Parcial",evaluation.getName());
    }
}
