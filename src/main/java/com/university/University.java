package com.university;

import com.university.classroom.Student;
import com.university.classroom.Course;
import com.university.evaluation.Evaluation;

import java.util.*;

public class University {
    private List<Student> students;
    private List<Course> courses;
    private List<Evaluation> evaluations;

    public University() {
        this.students = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.evaluations = new ArrayList<>();
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course){
        this.courses.add(course);
    }

    public List<Evaluation> getEvaluations() {return evaluations;}

    public void addEvaluation(Evaluation evaluation){
        this.evaluations.add(evaluation);
    }

    public void addStudent(Student student){
        this.students.add(student);
    }

    public Map<String, Integer> getStudentCourseCounts() {
        Map<String, Integer> studentCounts = new HashMap<>();
        for (Student student : students) {
            studentCounts.put(student.getName(), student.getCourseCount());
        }
        return studentCounts;
    }
}