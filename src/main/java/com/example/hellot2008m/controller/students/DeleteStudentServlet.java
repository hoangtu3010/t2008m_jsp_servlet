package com.example.hellot2008m.controller.students;

import com.example.hellot2008m.entity.Student;
import com.example.hellot2008m.model.GenericModel;
import com.example.hellot2008m.model.StudentModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericModel<Student> studentModel = new GenericModel<>(Student.class);
        int id = Integer.parseInt(req.getParameter("id"));
        studentModel.delete(id);
        resp.sendRedirect("/students/list");
    }
}
