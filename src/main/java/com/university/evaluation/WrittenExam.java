package com.university.evaluation;

public class WrittenExam extends Evaluation {
    public WrittenExam(String name, String studentName,String subjectName, String evaluationType) {
        super(name, studentName, subjectName,evaluationType);
    }

    public double getGrade(){
        double sum = 0;
        for (double grade : getGrades()){
            sum += grade;
        }
        return sum/(getGrades().size());
    }
}
