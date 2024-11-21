package com.university.readersTest;

import static org.junit.jupiter.api.Assertions.*;

import com.university.readers.CreatorA;
import com.university.University;
import com.university.classroom.Course;
import com.university.classroom.Student;
import com.university.evaluation.criteria.Criteria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreatorATest {
    private University university;
    private Criteria criteriaProcessor;
    private CreatorA creator1;

    @BeforeEach
    public void setUp() {
        university = new University();
        criteriaProcessor = new Criteria();
        creator1 = new CreatorA();
    }

    @Test
    public void testCreate() {
        String parts = "578,Political Science,Olivia Red,olivia.red@student.org,Prof. Sam";
        creator1.create(parts, university, criteriaProcessor);
        assertEquals(1, university.getStudents().size());
        assertEquals(1, university.getCourses().size());
        Student student = university.getStudents().getFirst();
        Course course = university.getCourses().getFirst();
        assertEquals("Olivia Red", student.getName());
        assertEquals("Political Science", course.getName());
        assertTrue(student.getCourses().contains(course));
        assertTrue(course.getStudents().contains(student));
    }
}