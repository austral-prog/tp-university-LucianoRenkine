package com.university.util;

import com.university.model.Course;
import com.university.model.CourseEnrollment;
import com.university.model.CourseMarks;
import com.university.model.Student;
import com.university.model.Exam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader2 {
    public List<CourseMarks> readMarks(String filePath) throws IOException {
        List<CourseMarks> marks = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // Leer la cabecera
            while ((line = br.readLine()) != null) { // Leer cada línea
                String[] values = line.split(",");
                int mark = Integer.parseInt(values[5]);

                if (values.length < 6) {
                    System.err.println("Error: Línea incompleta en el archivo CSV: " + line);
                    continue;
                }

                // Crear los objetos
                Student student = new Student(values[0]);   // Nombre del estudiante
                Course course = new Course(values[1]);      // Asignatura del curso
                Exam exam = new Exam(values[2], values[3]); // Examen

                CourseMarks courseMarks = new CourseMarks(student, course, exam, mark);

                // Agregar a la lista
                if (!marks.contains(courseMarks)) {
                    marks.add(courseMarks);
                }
            }
        }
        return marks;
    }
}
