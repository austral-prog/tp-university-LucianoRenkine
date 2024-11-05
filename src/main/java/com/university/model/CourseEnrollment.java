package com.university.model;

import java.util.Objects;

public class CourseEnrollment {
    private Student student;
    private Course course;

    public CourseEnrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }
    // Override equals to compare by student and course
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseEnrollment that = (CourseEnrollment) o;
        return Objects.equals(student.getName(), that.student.getName()) &&
               Objects.equals(course.getSubject(), that.course.getSubject());
    }

    // Override hashCode to be consistent with equals
    @Override
    public int hashCode() {
        return Objects.hash(student.getName(), course.getSubject());
    }
}