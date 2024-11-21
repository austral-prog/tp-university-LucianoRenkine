package com.university.create;

import com.university.University;

import java.util.List;

public interface CreateEntity <T> {
    T getOrCreate(String string, List<T> entities, University university);
}