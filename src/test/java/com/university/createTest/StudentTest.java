package com.university.createTest;

import static org.junit.jupiter.api.Assertions.*;

import com.university.create.CreateStudent;
import com.university.University;
import com.university.classroom.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class StudentTest {
private List<Student> students;
    private University university;
    private CreateStudent studentCreator;

    @BeforeEach
    public void setUp() {
        students = new ArrayList<>();
        university = new University();
        studentCreator = new CreateStudent(students);
    }

    @Test
    public void testCreateStudent() {
        String studentName = "Alice";
        String email = "alice@example.com";
        String inputData = "Some, data," + studentName + "," + email;
        Student student = studentCreator.getOrCreate(inputData, students, university);
        assertEquals(studentName, student.getName());
        assertEquals(email, student.getEmail());
        assertTrue(university.getStudents().contains(student));
    }

    @Test
    public void testStudentExists() {
        String studentName = "Bob";
        String email = "bob@example.com";
        String inputData = "Some, data," + studentName + "," + email;
        studentCreator.getOrCreate(inputData, students, university);
        Student student = studentCreator.getOrCreate(inputData, students, university);
        assertEquals(studentName, student.getName());
        assertEquals(email, student.getEmail());
        assertEquals(1, university.getStudents().size());
    }

    @Test
    public void testCreateWithDifferentData() {
        String studentName1 = "Charlie";
        String email1 = "charlie@example.com";
        String inputData1 = "Some, data," + studentName1 + "," + email1;
        String studentName2 = "David";
        String email2 = "david@example.com";
        String inputData2 = "Some, data," + studentName2 + "," + email2;
        studentCreator.getOrCreate(inputData1, students, university);
        studentCreator.getOrCreate(inputData2, students, university);
        assertEquals(2, university.getStudents().size());
        assertTrue(university.getStudents().stream().anyMatch(s -> s.getName().equals(studentName1)));
        assertTrue(university.getStudents().stream().anyMatch(s -> s.getName().equals(studentName2)));
    }

}