package com.university.readers;

import com.university.University;
import com.university.evaluation.criteria.Criteria;

import java.io.BufferedWriter;
import java.io.IOException;

public interface Writers {
    void write(University university, Criteria criteriaProcessor, BufferedWriter writer) throws IOException;
}