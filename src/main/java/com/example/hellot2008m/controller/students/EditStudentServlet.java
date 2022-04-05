package com.example.hellot2008m.controller.students;

import com.example.hellot2008m.entity.Student;
import com.example.hellot2008m.model.GenericModel;
import com.example.hellot2008m.model.StudentModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditStudentServlet extends HttpServlet {
    private final GenericModel<Student> model = new GenericModel<>(Student.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Student obj = model.findById(id);
        if (obj == null){
            resp.setStatus(404);
            resp.getWriter().println("Not found");
        }else {
            req.setAttribute("obj", obj);
            req.getRequestDispatcher("/admin/students/form.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String rollName = req.getParameter("rollName");
        String email = req.getParameter("email");
        String name = req.getParameter("name");
        Student student = new Student(id, rollName, email, name);
        GenericModel<Student> studentModel = new GenericModel<>(Student.class);
        studentModel.update(id, student);
        resp.sendRedirect("/students/list");
    }
}
