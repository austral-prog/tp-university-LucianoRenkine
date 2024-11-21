package com.university.create;

import com.university.University;
import com.university.classroom.Course;

import java.util.List;

public class CreateCourse implements CreateEntity<Course> {
    private List<Course> courses;

    public CreateCourse(List<Course> courses) {
        this.courses = courses;
    }

    public Course getOrCreate(String parts, List<Course> courses, University university) {
        String[] params = parts.split(",");
        int classroom = Integer.parseInt(params[0]);
        String subject = params[1];
        Course course = findCourseBySubject(subject, university);
        if (course == null) {
            course = new Course(classroom, subject);
            university.getCourses().add(course);
        }
        return course;
    }

    private Course findCourseBySubject(String subject, University university) {
        for (Course course : university.getCourses()) {
            if (course.getName().equals(subject)) {
                return course;
            }
        }
        return null;
    }
}