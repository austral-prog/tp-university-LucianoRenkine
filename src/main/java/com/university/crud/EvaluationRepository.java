package com.university.crud;

import com.university.classroom.Course;
import com.university.evaluation.Evaluation;

import java.util.HashMap;
import java.util.Map;

public class EvaluationRepository implements CRUDRepository<Evaluation> {
    private final Map<Integer, Evaluation> evalRepo = new HashMap<>();

    @Override
    public void create(Evaluation entity) {
        evalRepo.put(entity.getId(), entity);
    }

    @Override
    public Evaluation read(int id) {
        return evalRepo.get(id);
    }

    @Override
    public void update(int id, Evaluation entity) {
        evalRepo.put(id, entity);
    }

    @Override
    public void delete(int id) {
        evalRepo.remove(id);
    }

    @Override
    public String getIdentifier() {
        return "Evaluation";
    }

    @Override
    public Class<Evaluation> getEntityClass() {
        return Evaluation.class;
    }

    public Map<Integer, Evaluation> getRepo() {
        return evalRepo;
    }
}