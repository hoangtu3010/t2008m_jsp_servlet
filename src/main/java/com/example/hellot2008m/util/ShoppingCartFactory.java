package com.example.hellot2008m.util;

import com.example.hellot2008m.entity.ShoppingCart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ShoppingCartFactory {
    public static final String SHOPPING_CART_NAME = "shoppingCart";

    public static ShoppingCart getShoppingCartFromSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute(SHOPPING_CART_NAME);
        if (shoppingCart == null){
            shoppingCart = new ShoppingCart();
        }
        return shoppingCart;
    }

    public static void setShoppingCartToSession(HttpServletRequest request, ShoppingCart shoppingCart){
        HttpSession session = request.getSession();
        session.setAttribute(SHOPPING_CART_NAME, shoppingCart);
    }
}
