package com.university.readers;

import com.university.University;
import com.university.evaluation.criteria.Criteria;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class WriterC implements Writers {
    @Override
    public void write(University university, Criteria criteria, BufferedWriter writer) throws IOException {
        writer.write("Student,Subject,Status\n");
        List<String> results = criteria.evaluateStudents(university);
        for (String result : results) {
            writer.write(result);
            writer.write("\n");
        }
    }
}