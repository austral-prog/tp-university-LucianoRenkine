package com.university;

import com.university.cli.UniCLI;
import com.university.crud.CRUDRepository;
import com.university.crud.CourseRepository;
import com.university.crud.EvaluationRepository;
import com.university.crud.StudentRepository;

public class RunCLI {
    public static void main(String[] args) {
        UniCLI cli = new UniCLI();
        StudentRepository studentRepository = new StudentRepository();
        CourseRepository courseRepository = new CourseRepository();
        EvaluationRepository evaluationRepository = new EvaluationRepository();
        CRUDRepository<?>[] repositories = {studentRepository, courseRepository, evaluationRepository};
        cli.runCLI(repositories);
    }
}
