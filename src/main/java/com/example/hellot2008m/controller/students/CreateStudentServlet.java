package com.example.hellot2008m.controller.students;

import com.example.hellot2008m.entity.Student;
import com.example.hellot2008m.model.GenericModel;
import com.example.hellot2008m.model.StudentModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student obj = new Student();
        obj.setId(0);
        obj.setRollName("");
        obj.setName("");
        obj.setEmail("");

        req.setAttribute("obj", obj);
        req.getRequestDispatcher("/admin/students/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rollName = req.getParameter("rollName");
        String email = req.getParameter("email");
        String name = req.getParameter("name");
        Student student = new Student(0, rollName, email, name);

        if (!student.isValid()){
            Student obj = new Student();
            obj.setId(0);
            obj.setRollName("");
            obj.setName("");
            obj.setEmail("");

            req.setAttribute("obj", obj);
            req.setAttribute("errors", student.getErrors());
            req.getRequestDispatcher("/admin/students/form.jsp").forward(req, resp);
            return;
        }

        GenericModel<Student> studentModel = new GenericModel<>(Student.class);
        studentModel.save(student);
        resp.sendRedirect("/students/list");
    }
}
