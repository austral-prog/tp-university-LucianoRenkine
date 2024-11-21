package com.university.readers;

import com.university.create.CreateEvaluation;
import com.university.University;
import com.university.evaluation.criteria.Criteria;
import com.university.evaluation.Evaluation;

public class CreatorB implements Creators {
    @Override
    public void create(String parts, University university, Criteria criteriaProcessor) {
        CreateEvaluation evaluationCreator = new CreateEvaluation(university.getEvaluations());
        Evaluation evaluation = evaluationCreator.getOrCreate(parts, university.getEvaluations(), university);
        university.addEvaluation(evaluation);
    }
}
