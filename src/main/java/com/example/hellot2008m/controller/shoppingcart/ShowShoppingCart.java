package com.example.hellot2008m.controller.shoppingcart;

import com.example.hellot2008m.entity.ShoppingCart;
import com.example.hellot2008m.util.ShoppingCartFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowShoppingCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShoppingCart shoppingCart = ShoppingCartFactory.getShoppingCartFromSession(req);
        req.setAttribute("shoppingCart", shoppingCart);
        req.getRequestDispatcher("/admin/carts/list.jsp").forward(req, resp);
    }
}
