package com.university.csv;

import com.university.University;

import java.io.BufferedWriter;
import com.university.readers.Writers;
import com.university.evaluation.criteria.Criteria;

import java.io.*;

public class Writer {
    public static void writeCSV(String filePath, University university, Criteria criteriaProcessor, Writers fileWriter) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            fileWriter.write(university, criteriaProcessor, writer);
        }
    }
}