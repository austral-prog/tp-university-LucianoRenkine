package com.university.createTest;

import static org.junit.jupiter.api.Assertions.*;

import com.university.create.CreateCourse;
import com.university.University;
import com.university.classroom.Course;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class CourseTest {private List<Course> courses;
    private University university;
    private CreateCourse courseCreator;

    @BeforeEach
    public void setUp() {
        courses = new ArrayList<>();
        university = new University();
        courseCreator = new CreateCourse(courses);
    }

    @Test
    public void testCourseNotExist() {
        String courseData = "101,Math";
        Course course = courseCreator.getOrCreate(courseData, courses, university);
        assertNotNull(course);
        assertEquals(101, course.getClassroom());
        assertEquals("Math", course.getName());
        assertEquals(1, university.getCourses().size());
    }

    @Test
    public void testCourseAlreadyExists() {
        Course existingCourse = new Course(101, "Math");
        courses.add(existingCourse);
        String courseData = "101,Math";
        Course course = courseCreator.getOrCreate(courseData, courses, university);
        assertNotNull(course);
        assertEquals(existingCourse.toString(), course.toString());
        assertEquals(1, courses.size());
    }

}