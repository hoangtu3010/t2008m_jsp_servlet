package com.example.hellot2008m.model;

import com.example.hellot2008m.entity.Product;
import com.example.hellot2008m.util.ConnectionHelper;
import com.example.hellot2008m.util.SQLConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductModel {
    public boolean Save(Product product){
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQLConfig.INSERT);
            statement.setString(1, product.getName());
            statement.setString(2, product.getThumbnail());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, 1);
            statement.execute();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public List<Product> getAll() {
        List<Product> lstProduct = new ArrayList<>();
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQLConfig.SELECT);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String thumbnail = resultSet.getString("thumbnail");
                double price = resultSet.getDouble("price");
                int status = resultSet.getInt("status");
                Product product = new Product(id, name, thumbnail, price, status);
                lstProduct.add(product);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return lstProduct;
    }

    public Product getDetail(int id){
        Product obj = null;
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQLConfig.WHERE);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                String name = resultSet.getString("name");
                String thumbnail = resultSet.getString("thumbnail");
                double price = resultSet.getDouble("price");
                int status = resultSet.getInt("status");
                obj = new Product(id, name, thumbnail, price, status);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return obj;
    }

    public boolean editProduct(int id, Product product){
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQLConfig.UPDATE);
            statement.setString(1, product.getName());
            statement.setString(2, product.getThumbnail());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getStatus());
            statement.setInt(5, id);
            statement.execute();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
//
//    public boolean deleteStudent(int id){
//        try {
//            Connection connection = ConnectionHelper.getConnection();
//            PreparedStatement statement = connection.prepareStatement(SQLConfig.DELETE);
//            statement.setInt(1, id);
//            statement.execute();
//            return true;
//        }catch (Exception e){
//            e.printStackTrace();
//            return false;
//        }
//    }
}
