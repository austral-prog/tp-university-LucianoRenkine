package com.university.model;

import java.util.Objects;

public class Exam {
    private String examType;
    private String examName;

    public Exam(String examType, String examName) {
        this.examName = examName;
        this.examType = examType;
    }

    public String getExamName() {
        return examName;
    }

    public String getExamType() {
        return examType;
    }

    // Override equals to compare by examType and examName
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exam exam = (Exam) o;
        return Objects.equals(examType, exam.examType) &&
               Objects.equals(examName, exam.examName);
    }

    // Override hashCode to be consistent with equals
    @Override
    public int hashCode() {
        return Objects.hash(examType, examName);
    }

}
