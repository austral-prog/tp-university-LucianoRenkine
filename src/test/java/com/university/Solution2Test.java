package com.university;

import com.university.model.Course;
import com.university.model.CourseMarks;
import com.university.model.Exam;
import com.university.model.Student;
import com.university.service.MarkService;
import com.university.util.CSVReader2;
import com.university.util.CSVWriter2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Solution2Test {

    private MarkService markService;
    private CSVReader2 reader;
    private String input2FilePath = "src/main/resources/input_2.csv";
    private String output2FilePath = "src/main/resources/solution_2.csv";

    @BeforeEach
    void setUp() {
        markService = new MarkService();
        reader = new CSVReader2();
    }

    @Test
    void testReadMarks() throws IOException {
        List<CourseMarks> marks = reader.readMarks(input2FilePath);
        assertNotNull(marks, "La lista de marcas no debe ser nula");
        assertFalse(marks.isEmpty(), "La lista de marcas no debe estar vacía");
    }

    @Test
    void testOrderedGrades() throws IOException {
        List<CourseMarks> marks = reader.readMarks(input2FilePath);
        List<Integer> grades = markService.orderedGrades(marks);
        assertNotNull(grades, "La lista de calificaciones no debe ser nula");
        assertFalse(grades.isEmpty(), "La lista de calificaciones no debe estar vacía");
    }

    @Test
    void testSortMarks() throws IOException {
        List<CourseMarks> marks = reader.readMarks(input2FilePath);
        List<CourseMarks> sortedMarks = markService.sortMarks(marks);

        assertNotNull(sortedMarks, "La lista de marcas ordenadas no debe ser nula");
        assertFalse(sortedMarks.isEmpty(), "La lista de marcas ordenadas no debe estar vacía");

        // Verificar el orden esperado de las calificaciones
        for (int i = 1; i < sortedMarks.size(); i++) {
            CourseMarks previous = sortedMarks.get(i - 1);
            CourseMarks current = sortedMarks.get(i);
            assertTrue(previous.compareTo(current) <= 0, "Las calificaciones deben estar ordenadas correctamente");
        }
    }

    @Test
    void testWriteSolution2() throws IOException {
        List<CourseMarks> marks = reader.readMarks(input2FilePath);
        List<CourseMarks> sortedMarks = markService.sortMarks(marks);

        CSVWriter2.writeSolution2(output2FilePath, sortedMarks);

        assertTrue(Files.exists(Paths.get(output2FilePath)), "El archivo de salida debe existir");
    }

    @Test
    void testCourseMarksEqualsAndHashCode() {
        Student student = new Student("Juan Perez");
        Course course = new Course("Matemáticas");
        Exam exam = new Exam("WRITTEN_EXAM", "Examen Final");
        CourseMarks mark1 = new CourseMarks(student, course, exam, 85);
        CourseMarks mark2 = new CourseMarks(student, course, exam, 85);

        assertEquals(mark1, mark2, "Los objetos CourseMarks deben ser iguales");
        assertEquals(mark1.hashCode(), mark2.hashCode(), "Los hashCode deben coincidir para objetos CourseMarks iguales");
    }

    @Test
    void testOrderedNames() throws IOException {
        List<CourseMarks> marks = reader.readMarks(input2FilePath);
        List<String> names = markService.orderedNames(marks);

        assertNotNull(names, "La lista de nombres no debe ser nula");
        assertFalse(names.isEmpty(), "La lista de nombres no debe estar vacía");
    }

    @Test
    void testOrderedCourses() throws IOException {
        List<CourseMarks> marks = reader.readMarks(input2FilePath);
        List<String> courses = markService.orderedCourses(marks);

        assertNotNull(courses, "La lista de cursos no debe ser nula");
        assertFalse(courses.isEmpty(), "La lista de cursos no debe estar vacía");
    }

    @Test
    void testOrderedExamNames() throws IOException {
        List<CourseMarks> marks = reader.readMarks(input2FilePath);
        List<String> exams = markService.orderedExamNames(marks);

        assertNotNull(exams, "La lista de nombres de exámenes no debe ser nula");
        assertFalse(exams.isEmpty(), "La lista de nombres de exámenes no debe estar vacía");
    }

    @Test
    void testOrderedExamTypes() throws IOException {
        List<CourseMarks> marks = reader.readMarks(input2FilePath);
        List<String> examTypes = markService.orderedExamTypes(marks);

        assertNotNull(examTypes, "La lista de tipos de exámenes no debe ser nula");
        assertFalse(examTypes.isEmpty(), "La lista de tipos de exámenes no debe estar vacía");
    }
    class UniversityGradeCalculationTests {

    private MarkService markService;
    private List<CourseMarks> marks;

    @BeforeEach
    void setUp() {
        markService = new MarkService();
        marks = new ArrayList<>();
    }

    @Test
    void testWrittenExamCalculation() {
        Student student = new Student("Juan Perez");
        Course course = new Course("Matemáticas");

        // Añadimos calificaciones de tipo WRITTEN_EXAM
        marks.add(new CourseMarks(student, course, new Exam("WRITTEN_EXAM", "Examen Final"), 80));
        marks.add(new CourseMarks(student, course, new Exam("WRITTEN_EXAM", "Examen Final"), 90));

        List<Integer> grades = markService.orderedGrades(marks);

        // Esperamos que la calificación promedio para "Examen Final" sea (80 + 90) / 2 = 85
        assertEquals(1, grades.size(), "Debe haber solo una calificación calculada para el Examen Final");
        assertEquals(85, grades.get(0), "La calificación del Examen Final debe ser el promedio correcto");
    }

    @Test
    void testOralExamCalculation() {
        Student student = new Student("María López");
        Course course = new Course("Historia");

        // Añadimos una calificación de tipo ORAL_EXAM
        marks.add(new CourseMarks(student, course, new Exam("ORAL_EXAM", "Oral Final"), 75));

        List<Integer> grades = markService.orderedGrades(marks);

        // Esperamos que la calificación para el examen oral sea la calificación directa
        assertEquals(1, grades.size(), "Debe haber solo una calificación calculada para el Examen Oral");
        assertEquals(75, grades.get(0), "La calificación del Examen Oral debe coincidir con el valor proporcionado");
    }

    @Test
    void testFinalPracticalWorkCalculation() {
        Student student = new Student("Carlos Gómez");
        Course course = new Course("Ciencia");

        // Añadimos calificaciones de tipo FINAL_PRACTICAL_WORK para "TP Final"
        marks.add(new CourseMarks(student, course, new Exam("FINAL_PRACTICAL_WORK", "TP Final"), 85));
        marks.add(new CourseMarks(student, course, new Exam("FINAL_PRACTICAL_WORK", "TP Final"), 95));

        List<Integer> grades = markService.orderedGrades(marks);

        // Esperamos que la calificación total para "TP Final" sea 85 + 95 = 180
        assertEquals(1, grades.size(), "Debe haber solo una calificación calculada para el TP Final");
        assertEquals(180, grades.get(0), "La calificación del TP Final debe ser la suma de los valores proporcionados");
    }

    @Test
    void testPracticalWorkCalculation() {
        Student student = new Student("Laura Díaz");
        Course course = new Course("Química");

        // Añadimos una calificación de tipo PRACTICAL_WORK
        marks.add(new CourseMarks(student, course, new Exam("PRACTICAL_WORK", "TP Parcial"), 88));

        List<Integer> grades = markService.orderedGrades(marks);

        // Esperamos que la calificación para el trabajo práctico sea la calificación directa
        assertEquals(1, grades.size(), "Debe haber solo una calificación calculada para el Trabajo Práctico");
        assertEquals(88, grades.get(0), "La calificación del Trabajo Práctico debe coincidir con el valor proporcionado");
    }
}
}
class UniversityGradeCalculationTests {

    private MarkService markService;
    private List<CourseMarks> marks;

    @BeforeEach
    void setUp() {
        markService = new MarkService();
        marks = new ArrayList<>();
    }

    @Test
    void testWrittenExamCalculation() {
        Student student = new Student("Juan Perez");
        Course course = new Course("Matemáticas");

        // Añadimos calificaciones de tipo WRITTEN_EXAM
        marks.add(new CourseMarks(student, course, new Exam("WRITTEN_EXAM", "Examen Final"), 80));
        marks.add(new CourseMarks(student, course, new Exam("WRITTEN_EXAM", "Examen Final"), 90));

        List<Integer> grades = markService.orderedGrades(marks);

        // Esperamos que la calificación promedio para "Examen Final" sea (80 + 90) / 2 = 85
        assertEquals(1, grades.size(), "Debe haber solo una calificación calculada para el Examen Final");
        assertEquals(85, grades.get(0), "La calificación del Examen Final debe ser el promedio correcto");
    }

    @Test
    void testOralExamCalculation() {
        Student student = new Student("María López");
        Course course = new Course("Historia");

        // Añadimos una calificación de tipo ORAL_EXAM
        marks.add(new CourseMarks(student, course, new Exam("ORAL_EXAM", "Oral Final"), 75));

        List<Integer> grades = markService.orderedGrades(marks);

        // Esperamos que la calificación para el examen oral sea la calificación directa
        assertEquals(1, grades.size(), "Debe haber solo una calificación calculada para el Examen Oral");
        assertEquals(75, grades.get(0), "La calificación del Examen Oral debe coincidir con el valor proporcionado");
    }

    @Test
    void testFinalPracticalWorkCalculation() {
        Student student = new Student("Carlos Gómez");
        Course course = new Course("Ciencia");

        // Añadimos calificaciones de tipo FINAL_PRACTICAL_WORK para "TP Final"
        marks.add(new CourseMarks(student, course, new Exam("FINAL_PRACTICAL_WORK", "TP Final"), 85));
        marks.add(new CourseMarks(student, course, new Exam("FINAL_PRACTICAL_WORK", "TP Final"), 95));

        List<Integer> grades = markService.orderedGrades(marks);

        // Esperamos que la calificación total para "TP Final" sea 85 + 95 = 180
        assertEquals(1, grades.size(), "Debe haber solo una calificación calculada para el TP Final");
        assertEquals(180, grades.get(0), "La calificación del TP Final debe ser la suma de los valores proporcionados");
    }

    @Test
    void testPracticalWorkCalculation() {
        Student student = new Student("Laura Díaz");
        Course course = new Course("Química");

        // Añadimos una calificación de tipo PRACTICAL_WORK
        marks.add(new CourseMarks(student, course, new Exam("PRACTICAL_WORK", "TP Parcial"), 88));

        List<Integer> grades = markService.orderedGrades(marks);

        // Esperamos que la calificación para el trabajo práctico sea la calificación directa
        assertEquals(1, grades.size(), "Debe haber solo una calificación calculada para el Trabajo Práctico");
        assertEquals(88, grades.get(0), "La calificación del Trabajo Práctico debe coincidir con el valor proporcionado");
    }
}