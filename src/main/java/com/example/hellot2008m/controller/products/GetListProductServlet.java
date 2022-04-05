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
import java.util.List;

public class GetListProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericModel<Product> productModel  = new GenericModel<>(Product.class);

        List<Product> listProducts = productModel.getAll();

        req.setAttribute("listProducts", listProducts);

        req.getRequestDispatcher("/admin/products/list.jsp").forward(req, resp);
    }
}
