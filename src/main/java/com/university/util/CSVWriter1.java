package com.university.util;

import com.university.model.Course;
import com.university.model.CourseEnrollment;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.List;

public class CSVWriter1 {
    public static void writeSolution(String filePath, List<String> names, List<Integer> courseCount) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Student_Name,Course_Count\n");
            for (String name : names) {
                int index = names.indexOf(name);
                int course_count = courseCount.get(index);
                writer.write(name + "," + course_count + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
