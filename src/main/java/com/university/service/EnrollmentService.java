package com.university.service;

import com.university.model.CourseEnrollment;
import com.university.model.Student;

import java.util.*;

public class EnrollmentService {
    public Map<String, Integer> calculateEnrollmentCount(List<CourseEnrollment> enrollments) {
        Map<String, Integer> enrollmentCount = new HashMap<>();

        for (CourseEnrollment enrollment : enrollments) {
            Student student = enrollment.getStudent();
            String studentName = student.getName();
            enrollmentCount.put(studentName, enrollmentCount.getOrDefault(studentName, 0) + 1);
        }
        return enrollmentCount;
    }
}
