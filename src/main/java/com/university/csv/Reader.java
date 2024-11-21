package com.university.csv;

import com.university.readers.Creators;
import com.university.University;
import com.university.evaluation.criteria.Criteria;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Reader {
    public static void processCSV(String filePath, University university, Creators creator, Criteria criteriaProcessor) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String parts = line;
                creator.create(parts, university, criteriaProcessor);
            }
        }
        catch (FileNotFoundException e) {
            System.err.println("Error: File not found - " + filePath);
            throw new IllegalArgumentException("The specified file was not found: " + filePath, e);
            }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
