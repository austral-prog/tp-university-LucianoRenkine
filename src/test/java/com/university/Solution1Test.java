package com.university;

import com.university.model.Course;
import com.university.model.CourseEnrollment;
import com.university.model.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Solution1Test {
    @Test
    public void testStudentCreation() {
        Student student = new Student("Alice");
        assertEquals("Alice", student.getName(), "The student's name should be Alice");
    }

    @Test
    public void testCourseCreation() {
        Course course = new Course("Mathematics");
        assertEquals("Mathematics", course.getSubject(), "The course subject should be Mathematics");
    }

    @Test
    public void testCourseEnrollment() {
        Student student = new Student("Alice");
        Course course = new Course("Mathematics");
        CourseEnrollment enrollment = new CourseEnrollment(student, course);

        assertEquals("Alice", enrollment.getStudent().getName(), "Enrollment should store the correct student name");
        assertEquals("Mathematics", enrollment.getCourse().getSubject(), "Enrollment should store the correct course subject");
    }

    @Test
    public void testEnrollmentEquality() {
        Student student1 = new Student("Alice");
        Course course1 = new Course("Mathematics");
        CourseEnrollment enrollment1 = new CourseEnrollment(student1, course1);

        Student student2 = new Student("Alice");
        Course course2 = new Course("Mathematics");
        CourseEnrollment enrollment2 = new CourseEnrollment(student2, course2);

        assertEquals(enrollment1, enrollment2, "Enrollments with the same student and course should be equal");
    }
}