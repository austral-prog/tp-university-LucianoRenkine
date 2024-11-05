package com.university.app;

import com.university.model.CourseEnrollment;
import com.university.util.CSVReader;
import com.university.util.CSVWriter;
import com.university.service.EnrollmentService;

import java.io.IOException;
import java.util.*;

public class App {
    public static void main(String[] args) throws IOException {
        String inputFilePath = "src/main/resources/input.csv";
        String outputFilePath = "src/main/resources/solution.csv";

        CSVReader reader = new CSVReader();
        CSVWriter writer = new CSVWriter();
        EnrollmentService enrollmentService = new EnrollmentService();

        List<CourseEnrollment> enrollments = reader.readEnrollments("src/main/resources/input.csv");
        Map<String, Integer> enrollmentMap = enrollmentService.calculateEnrollmentCount(enrollments);
        List<String> names = new ArrayList<>(enrollmentMap.keySet());
        Collections.sort(names);
        List<Integer> courseCount = new ArrayList<>();
        for (String name : names) {
            courseCount.add(enrollmentMap.get(name));
        }

        writer.writeSolution("src/main/resources/solution.csv", names, courseCount);
    }
}
