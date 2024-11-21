package com.university.readers;

import com.university.University;
import com.university.evaluation.criteria.Criteria;

public interface Creators {
    void create(String string, University university, Criteria criteria);
}
