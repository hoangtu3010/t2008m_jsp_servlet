package com.example.hellot2008m.controller.shoppingcart;

import com.example.hellot2008m.entity.Product;
import com.example.hellot2008m.entity.ShoppingCart;
import com.example.hellot2008m.model.ProductModel;
import com.example.hellot2008m.util.ShoppingCartFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddProductToCart extends HttpServlet {
    private ProductModel productModel;

    public AddProductToCart() {
        productModel = new ProductModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productId = 0;
        int quantity = 0;
        try {
            productId = Integer.parseInt(req.getParameter("productId"));
            quantity = Integer.parseInt(req.getParameter("quantity"));
        }catch (Exception e){

        }

        if (productId <= 0 || quantity <= 0){
            resp.getWriter().println("Invalid quantity or product id.");
            return;
        }

        Product product = productModel.getDetail(productId);
        if (product == null){
            resp.getWriter().write("product is not found");
            return;
        }

        ShoppingCart shoppingCart = ShoppingCartFactory.getShoppingCartFromSession(req);
        shoppingCart.AddItemToCart(product, quantity);
        ShoppingCartFactory.setShoppingCartToSession(req, shoppingCart);
    }
}
