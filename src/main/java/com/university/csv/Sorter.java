package com.university.csv;

import com.university.evaluation.Evaluation;

import java.util.*;

public class Sorter {
    public static List<Map.Entry<String, Integer>> sortByName(Map<String, Integer> data) {
        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(data.entrySet());
        sortedEntries.sort(Map.Entry.comparingByKey());
        return sortedEntries;
    }

    public static List<Evaluation> sortEvaluations(List<Evaluation> evaluations) {
        evaluations.sort(Comparator
                .comparing(Evaluation::getSubjectName)
                .thenComparing(Evaluation::getName)
                .thenComparing(Evaluation::getStudentName)
        );
        return evaluations;
    }
}
