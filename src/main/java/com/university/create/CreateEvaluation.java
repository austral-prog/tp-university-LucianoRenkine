package com.university.create;

import com.university.University;
import com.university.classroom.Student;
import com.university.evaluation.*;

import java.util.List;

public class CreateEvaluation implements CreateEntity<Evaluation>{
private List<Evaluation> evaluations;

    public CreateEvaluation(List<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }

    public Evaluation getOrCreate(String parts, List<Evaluation> evaluations, University university) {
        String[] params = parts.split(",");
        String studentName = params[0].trim();
        String subjectName = params[1].trim();
        String evaluationType = params[2].trim();
        String evaluationName = params[3].trim();
        double grade = Double.parseDouble(params[5].trim());

        Evaluation evaluation = findEvaluation(subjectName, evaluationName, studentName, evaluationType, university);

        Student student = findStudentByName(studentName, university);
        if (student == null) {
            throw new IllegalArgumentException("Student not found in university: " + studentName);
        }

        if (evaluation == null) {
            evaluation = createEvaluation(subjectName, evaluationName, studentName, evaluationType);
            university.getEvaluations().add(evaluation);
            student.addEvaluation(evaluation);
        } else if (!student.getEvaluations().contains(evaluation)) {
            student.addEvaluation(evaluation);
        }

        evaluation.addGrades(grade);
        return evaluation;
    }

    private Evaluation findEvaluation (String subjectName, String evaluationName, String studentName, String
            evaluationType, University university){
        for (Evaluation e : university.getEvaluations()) {
            if (e.getSubjectName().equals(subjectName) &&
                    e.getName().equals(evaluationName) &&
                    e.getStudentName().equals(studentName) &&
                    e.getEvaluationType().equals(evaluationType)) {
                return e;
            }
        }
        return null;
    }
    public static Evaluation createEvaluation(String subjectName, String evaluationName, String studentName, String evaluationType) {
        switch (evaluationType) {
            case "WRITTEN_EXAM":
                return new WrittenExam(evaluationName, studentName, subjectName, evaluationType);
            case "PRACTICAL_WORK":
                return new PracticalWork(evaluationName, studentName, subjectName, evaluationType);
            case "ORAL_EXAM":
                return new OralExam(evaluationName, studentName, subjectName, evaluationType);
            case "FINAL_PRACTICAL_WORK":
                return new FinalExam(evaluationName, studentName, subjectName, evaluationType);
            default:
                throw new IllegalArgumentException("Unknown evaluation type: " + evaluationType);
        }
    }

    private Student findStudentByName(String studentName, University university) {
        for (Student student : university.getStudents()) {
            if (student.getName().equals(studentName)) {
                return student;
            }
        }
        return null;
    }
}
