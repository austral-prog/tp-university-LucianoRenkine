package com.university.model;

import com.university.model.CourseEnrollment;

import java.util.Objects;

public class CourseMarks implements Comparable<CourseMarks> {
    private Student student;
    private Course course;
    private Exam exam;
    private int mark;

    public CourseMarks(Student student, Course course, Exam exam, int mark) {
        this.student = student;
        this.course = course;
        this.exam = exam;
        this.mark = mark;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public Exam getExam() {
        return exam;
    }

    public int getMark() {
        return mark;
    }

    @Override
    public int compareTo(CourseMarks other) {
        int courseCompare = this.course.getSubject().compareTo(other.course.getSubject());
        if (courseCompare != 0) return courseCompare;

        int examCompare = this.exam.getExamName().compareTo(other.exam.getExamName());
        if (examCompare != 0) return examCompare;

        return this.student.getName().compareTo(other.student.getName());
    }

    // Override equals to compare by student, course, and exam
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseMarks that = (CourseMarks) o;
        return Objects.equals(student.getName(), that.student.getName()) &&
                Objects.equals(course.getSubject(), that.course.getSubject()) &&
                Objects.equals(exam, that.exam);
    }

    // Override hashCode to be consistent with equals
    @Override
    public int hashCode() {
        return Objects.hash(student.getName(), course.getSubject(), exam);
    }
}
