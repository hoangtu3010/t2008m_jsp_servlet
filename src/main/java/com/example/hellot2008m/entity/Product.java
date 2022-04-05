package com.example.hellot2008m.entity;

import com.example.hellot2008m.annotation.Column;
import com.example.hellot2008m.annotation.Id;
import com.example.hellot2008m.annotation.Table;

import java.util.HashMap;
import java.util.Objects;

@Table(name = "products")
public class Product {
    @Id(autoIncrement = true)
    @Column(name = "id", type = "INT")
    private int id;

    @Column(name = "name", type = "VARCHAR(255)")
    private String name;

    @Column(name = "thumbnail", type = "TEXT")
    private String thumbnail;

    @Column(name = "price", type = "DOUBLE")
    private double price;

    @Column(name = "status", type = "INT")
    private int status;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", price=" + price +
                ", status=" + status +
                '}';
    }

    public Product() {
    }

    public Product(int id, String name, String thumbnail, double price, int status) {
        this.id = id;
        this.name = name;
        this.thumbnail = thumbnail;
        this.price = price;
        this.status = status;
    }

    public Product(String name, String thumbnail, double price, int status) {
        this.name = name;
        this.thumbnail = thumbnail;
        this.price = price;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    private HashMap<String, String> errors;

    private void checkValid(){
        this.errors = new HashMap<>();

        if(this.name == null || this.name.length() == 0){
            this.errors.put("name", "Name is required");
        }

        if(this.thumbnail == null || this.thumbnail.length() == 0){
            this.errors.put("thumbnail", "Thumbnail is required");
        }

        if(this.price <= 0){
            this.errors.put("price", "Price is required");
        }
    }

    public HashMap<String, String> getErrors() {
        checkValid();
        return errors;
    }

    public boolean isValid(){
        checkValid();
        return errors == null || errors.size() == 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
