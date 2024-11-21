package com.university.readersTest;

import com.university.readers.Creators;
import com.university.readers.CreatorC;
import com.university.University;
import com.university.evaluation.criteria.Criteria;
import com.university.evaluation.criteria.Criterion;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;


public class CreatorCTest {
    @Test
    public void testCreate() {
        University university = new University();
        Criteria criteriaProcessor = new Criteria();
        Creators creator = new CreatorC();
        String parts = "Geography, AVERAGE_ABOVE_VALUE, 6.0, TP1";
        creator.create(parts, university, criteriaProcessor);
        List<Criterion> criteriaList = criteriaProcessor.getCriteriaList();
        assertEquals(1, criteriaList.size());
        Criterion criterion = criteriaList.getFirst();
        assertEquals("Geography", criterion.getSubjectName());

    }
}
