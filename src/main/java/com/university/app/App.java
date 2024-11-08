package com.university.app;

import com.university.model.CourseEnrollment;
import com.university.service.MarkService;
import com.university.model.CourseMarks;
import com.university.util.CSVReader1;
import com.university.util.CSVReader2;
import com.university.util.CSVWriter1;
import com.university.util.CSVWriter2;
import com.university.service.EnrollmentService;

import java.io.IOException;
import java.util.*;

public class App {
    public static void main(String[] args) throws IOException {
        String inputFilePath = "src/main/resources/input.csv";
        String outputFilePath = "src/main/resources/solution.csv";

        CSVReader1 reader = new CSVReader1();
        CSVWriter1 writer = new CSVWriter1();
        EnrollmentService enrollmentService = new EnrollmentService();

        List<CourseEnrollment> enrollments = reader.readEnrollments(inputFilePath);
        Map<String, Integer> enrollmentMap = enrollmentService.calculateEnrollmentCount(enrollments);
        List<String> names = new ArrayList<>(enrollmentMap.keySet());
        Collections.sort(names);
        List<Integer> courseCount = new ArrayList<>();
        for (String name : names) {
            courseCount.add(enrollmentMap.get(name));
        }

        writer.writeSolution(outputFilePath, names, courseCount);

        String input2FilePath = "src/main/resources/input_2.csv";
        String output2FilePath = "src/main/resources/solution_2.csv";

        CSVReader2 reader2 = new CSVReader2();
        MarkService markService = new MarkService();

        try {
            // Leer los datos del CSV
            List<CourseMarks> marks = reader2.readMarks(input2FilePath);

            // Calcular y ordenar las notas
            markService.orderedGrades(marks);
            List<CourseMarks> sortedMarks = markService.sortMarks(marks);

            // Escribir el archivo de salida
            CSVWriter2.writeSolution2(output2FilePath, sortedMarks);
            System.out.println("Archivo solution_2.csv generado correctamente.");
        } catch (IOException e) {
            System.err.println("Error al leer o escribir el archivo CSV: " + e.getMessage());
        }
    }
}
