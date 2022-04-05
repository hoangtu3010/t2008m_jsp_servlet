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
import java.io.IOException;

public class CreateProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product obj = new Product();
        obj.setId(0);
        obj.setName("");
        obj.setThumbnail("");
        obj.setPrice(0);
        obj.setStatus(0);

        req.setAttribute("obj", obj);
        req.getRequestDispatcher("/admin/products/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String thumbnail = req.getParameter("thumbnail");
        double price = Double.parseDouble(req.getParameter("price")) ;
        int status = 1;
        Product product = new Product(0, name, thumbnail, price, status);

//        if (!product.isValid()){
//            Student obj = new Student();
//            obj.setId(0);
//            obj.setRollName("");
//            obj.setName("");
//            obj.setEmail("");
//
//            req.setAttribute("obj", obj);
//            req.setAttribute("errors", student.getErrors());
//            req.getRequestDispatcher("/admin/students/form.jsp").forward(req, resp);
//            return;
//        }

        GenericModel<Product> productModel = new GenericModel<>(Product.class);
        productModel.save(product);
        resp.sendRedirect("/products/list");
    }
}
