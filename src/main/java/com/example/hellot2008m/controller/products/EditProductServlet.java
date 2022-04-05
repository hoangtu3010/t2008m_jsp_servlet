package com.example.hellot2008m.controller.products;

import com.example.hellot2008m.entity.Product;
import com.example.hellot2008m.entity.Student;
import com.example.hellot2008m.model.GenericModel;
import com.example.hellot2008m.model.ProductModel;
import com.example.hellot2008m.model.StudentModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;

public class EditProductServlet extends HttpServlet {
    private final GenericModel<Product> model = new GenericModel<>(Product.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product obj = model.findById(id);
        if (obj == null){
            resp.setStatus(404);
            resp.getWriter().println("Not found");
        }else {
            HttpSession session = req.getSession();
            HashSet<Product> recentViewStudent = (HashSet<Product>) session.getAttribute("recentViewStudent");
            if (recentViewStudent == null){
                recentViewStudent = new HashSet<>();
            }
            recentViewStudent.add(obj);
            session.setAttribute("recentViewStudent", recentViewStudent);
            req.setAttribute("obj", obj);
            req.getRequestDispatcher("/admin/products/form.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String thumbnail = req.getParameter("thumbnail");
        double price = Double.parseDouble(req.getParameter("price"));
        Product product = new Product(id, name, thumbnail, price, 1);
        model.update(id, product);
        resp.sendRedirect("/products/list");
    }
}
