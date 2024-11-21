package com.university.readers;

import com.university.create.CreateCourse;
import com.university.create.CreateStudent;
import com.university.University;
import com.university.evaluation.criteria.Criteria;
import com.university.classroom.Course;
import com.university.classroom.Student;

public class CreatorA implements Creators {
    @Override
    public void create(String parts, University university, Criteria criteriaProcessor) {
        CreateStudent studentCreator = new CreateStudent(university.getStudents());
        CreateCourse courseCreator = new CreateCourse(university.getCourses());
        Student student = studentCreator.getOrCreate(parts, university.getStudents(), university);
        Course course = courseCreator.getOrCreate(parts, university.getCourses(), university);
        student.addToCourse(course);
        course.addStudent(student);
    }
}
