package com.university.util;

import com.university.model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader1 {
    public List<CourseEnrollment> readEnrollments(String filePath) throws IOException {
        List<CourseEnrollment> enrollments = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // Leer la cabecera
            while ((line = br.readLine()) != null) { // Leer cada línea
                String[] values = line.split(",");

                if (values.length < 5) {
                    System.err.println("Error: Línea incompleta en el archivo CSV: " + line);
                    continue;
                }

                // Crear los objetos de la fila
                Student student = new Student(values[2]);   // Nombre del estudiante
                Course course = new Course(values[1]);      // Asignatura del curso

                CourseEnrollment current = new CourseEnrollment(student, course);

                // Agregar la matrícula a la lista
                if (enrollments.contains(current)) {
                    int a = 0;
                }
                else {
                    enrollments.add(current);
                }
            }
        }
        return enrollments;
    }
}