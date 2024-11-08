package com.university.service;

import com.university.model.Course;
import com.university.model.CourseMarks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MarkService {
    public List<String> orderedNames(List<CourseMarks> data) {
        List<String> names = new ArrayList<>();
        for (CourseMarks marks : data) {
            String name = marks.getStudent().getName();
            if (names.contains(name)) {
                int a = 0;
            } else {
                names.add(name);
            }
        }
        return names;
    }

    public List<String> orderedCourses(List<CourseMarks> data) {
        List<String> courses = new ArrayList<>();
        for (CourseMarks marks : data) {
            String course = marks.getCourse().getSubject();
            if (courses.contains(course)) {
                int a = 0;
            } else {
                courses.add(course);
            }
        }
        return courses;
    }

    public List<String> orderedExamNames(List<CourseMarks> data) {
        List<String> exams = new ArrayList<>();
        for (CourseMarks marks : data) {
            String exam = marks.getExam().getExamName();
            exams.add(exam);
        }
        return exams;
    }

    public List<String> orderedExamTypes(List<CourseMarks> data) {
        List<String> exams = new ArrayList<>();
        for (CourseMarks marks : data) {
            String exam = marks.getExam().getExamType();
            exams.add(exam);
        }
        return exams;
    }

    private List<Integer> orderedMarks(List<CourseMarks> data) {
        List<Integer> marks_list = new ArrayList<>();
        for (CourseMarks marks : data) {
            int mark = marks.getMark();
            marks_list.add(mark);
        }
        return marks_list;
    }

    public List<Integer> orderedGrades(List<CourseMarks> data) {
    List<Integer> grades = new ArrayList<>();
    List<String> processedEntries = new ArrayList<>();

    for (CourseMarks courseMark : data) {
        String key = courseMark.getStudent().getName() + "-" +
                     courseMark.getCourse().getSubject() + "-" +
                     courseMark.getExam().getExamName();

        // Evitar duplicados en casos donde ya se haya procesado una entrada final
        if (processedEntries.contains(key)) {
            continue;
        }
        processedEntries.add(key);

        String type = courseMark.getExam().getExamType();
        int grade = 0;

        // Procesar según el tipo de examen
        if (type.equals("WRITTEN_EXAM") || type.equals("FINAL_PRACTICAL_WORK")) {
            List<Integer> marks = new ArrayList<>();
            for (CourseMarks currentCourseMark : data) {
                if (currentCourseMark.getStudent().getName().equals(courseMark.getStudent().getName()) &&
                    currentCourseMark.getCourse().getSubject().equals(courseMark.getCourse().getSubject()) &&
                    currentCourseMark.getExam().getExamName().equals(courseMark.getExam().getExamName())) {
                    marks.add(currentCourseMark.getMark());
                }
            }
            if (type.equals("WRITTEN_EXAM")) {
                // Calcular promedio para "WRITTEN_EXAM"
                for (int mark : marks) {
                    grade += mark;
                }
                grade = grade / marks.size(); // Promedio
            } else {
                // Calcular suma para "FINAL_PRACTICAL_WORK"
                for (int mark : marks) {
                    grade += mark;
                }
            }
            grades.add(grade);
        } else if (type.equals("PRACTICAL_WORK")) {
            grades.add(courseMark.getMark());
        } else if (type.equals("ORAL_EXAM")) {
            grades.add(courseMark.getMark());
        }
    }

    return grades;
}

    public List<CourseMarks> sortMarks(List<CourseMarks> marks) {
    marks.sort(Comparator
        .comparing((CourseMarks cm) -> cm.getCourse().getSubject())
        .thenComparing(cm -> cm.getExam().getExamName())
        .thenComparing(cm -> cm.getStudent().getName()));
    return marks;
    }
}