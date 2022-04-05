package com.example.hellot2008m.model;

import com.example.hellot2008m.entity.Student;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentModelTest {

    @Test
    void getAll() {
        StudentModel studentModel = new StudentModel();
        List<Student> students = studentModel.getAll();
        System.out.println(students.size());
        for (Student st : students
        ) {
            System.out.println(st.toString());
        }
    }

    @Test
    void getDetail() {
        StudentModel studentModel = new StudentModel();
        System.out.println(studentModel.getDetail(5));
    }
}