package com.example.hellot2008m.model;

import com.example.hellot2008m.annotation.Table;
import com.example.hellot2008m.entity.Product;
import com.example.hellot2008m.entity.Student;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GenericModelTest {

    @Test
    void save() {
        GenericModel<Product> studentGenericModel = new GenericModel<>(Product.class);
        studentGenericModel.save(new Product("Product 1", "anh 1", 1255, 1));
    }

    @Test
    void getAll() {
        GenericModel<Product> studentGenericModel = new GenericModel<>(Product.class);
        ArrayList<Product> products = (ArrayList<Product>) studentGenericModel.getAll();
        for (Product st : products
        ) {
            System.out.println(st.toString());
        }
    }

    @Test
    void findById() {
        GenericModel<Product> productGenericModel = new GenericModel<>(Product.class);
        Product product = productGenericModel.findById(1);
        System.out.println(product);
    }

    @Test
    void update() {
        GenericModel<Product> productGenericModel = new GenericModel<>(Product.class);
        Product product = new Product(1,"Iphone 11 (thường)","https://cdn1.viettelstore.vn/images/Product/ProductImage/medium/2132584444.jpeg", 1000, 1);
        productGenericModel.update(1, product);
    }

    @Test
    void delete() {
        GenericModel<Student> studentGenericModel = new GenericModel<>(Student.class);
        studentGenericModel.delete(5);
    }
}