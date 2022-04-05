package com.example.hellot2008m.controller.students;

import com.example.hellot2008m.entity.Product;
import com.example.hellot2008m.entity.Student;
import com.example.hellot2008m.model.GenericModel;
import com.example.hellot2008m.model.StudentModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetListStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericModel<Student> studentModel  = new GenericModel<>(Student.class);

        List<Student> listStudents = studentModel.getAll();

        req.setAttribute("listStudents", listStudents);

        req.getRequestDispatcher("/admin/students/list.jsp").forward(req, resp);
    }
}
