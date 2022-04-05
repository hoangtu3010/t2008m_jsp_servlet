package com.example.hellot2008m.entity;

import com.example.hellot2008m.annotation.Column;
import com.example.hellot2008m.annotation.Id;
import com.example.hellot2008m.annotation.Table;

import java.util.HashMap;
import java.util.Objects;

@Table(name = "students")
public class Student {
    @Id(autoIncrement = true)
    @Column(name = "id", type = "INT")
    private int id;

    @Column(name = "rollName", type = "VARCHAR(250)")
    private String rollName;

    @Column(name = "email", type = "VARCHAR(250)")
    private String email;

    @Column(name = "name", type = "VARCHAR(250)")
    private String name;

    public Student(){}

    public Student(int id, String rollName, String email, String name){
        this.id = id;
        this.rollName = rollName;
        this.email = email;
        this.name = name;
    }

    public Student(String rollName, String email, String name) {
        this.rollName = rollName;
        this.email = email;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    private HashMap<String, String> errors;

    private void checkValid(){
        this.errors = new HashMap<>();

        if(this.rollName == null || this.rollName.length() == 0){
            this.errors.put("rollName", "Roll name is required");
        }

        if(this.name == null || this.name.length() == 0){
            this.errors.put("name", "Name is required");
        }

        if(this.email == null || this.email.length() == 0){
            this.errors.put("email", "Email is required");
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

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", rollName='" + rollName + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRollName() {
        return rollName;
    }

    public void setRollName(String rollName) {
        this.rollName = rollName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
