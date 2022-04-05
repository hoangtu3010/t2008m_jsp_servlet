package com.example.hellot2008m.controller.products;

import com.example.hellot2008m.entity.Product;
import com.example.hellot2008m.model.GenericModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericModel<Product> productModel = new GenericModel<>(Product.class);
        int id = Integer.parseInt(req.getParameter("id"));
        productModel.delete(id);
        resp.sendRedirect("/products/list");
    }
}
