package com.university.util;

import com.university.model.CourseMarks;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriter2 {
    public static void writeSolution2(String outputFilePath, List<CourseMarks> marks) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            writer.write("Subject_Name,Evaluation_Name,Student_Name,Grade\n");

            for (CourseMarks mark : marks) {
                String subjectName = mark.getCourse().getSubject();
                String evaluationName = mark.getExam().getExamName();
                String studentName = mark.getStudent().getName();
                int grade = mark.getMark();

                writer.write(subjectName + "," + evaluationName + "," + studentName + "," + grade + "\n");
            }
        }
    }
}