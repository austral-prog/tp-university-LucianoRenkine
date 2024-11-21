package com.university.readers;

import com.university.University;
import com.university.evaluation.criteria.Criteria;
import com.university.csv.Sorter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class WriterA implements Writers {
    @Override
    public void write(University university, Criteria criteria, BufferedWriter writer) throws IOException {
        writer.write("Student_Name,Course_Count\n");
        Map<String, Integer> studentCounts = university.getStudentCourseCounts();
        List<Map.Entry<String, Integer>> sortedEntries = Sorter.sortByName(studentCounts);
        for (Map.Entry<String, Integer> entry : sortedEntries) {
            writer.write(entry.getKey() + "," + entry.getValue() + "\n");
        }
    }
}
