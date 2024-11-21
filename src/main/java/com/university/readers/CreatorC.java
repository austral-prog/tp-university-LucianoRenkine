package com.university.readers;

import com.university.University;
import com.university.evaluation.criteria.Criteria;
import com.university.evaluation.criteria.Criterion;

import java.util.Arrays;
import java.util.List;

public class CreatorC implements Creators {
    @Override
    public void create(String parts, University university, Criteria criteriaProcessor) {
        String[] params = parts.split(",");
        if (params.length >= 3) {
            String subjectName = params[0];
            String criteriaType = params[1];
            double criteriaValue = Double.parseDouble(params[2]);

            String[] evaluationNames = Arrays.copyOfRange(params, 3, params.length);
            Criterion criterion = new Criterion(subjectName, criteriaType, criteriaValue, List.of(evaluationNames));
            criteriaProcessor.addCriteria(criterion);
        }
    }
}
