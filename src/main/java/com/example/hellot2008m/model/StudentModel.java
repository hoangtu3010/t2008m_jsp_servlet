package com.example.hellot2008m.model;

import com.example.hellot2008m.entity.Student;
import com.example.hellot2008m.util.ConnectionHelper;
import com.example.hellot2008m.util.SQLConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentModel {
    public boolean Save(Student student){
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQLConfig.INSERT);
            statement.setString(1, student.getRollName());
            statement.setString(2, student.getEmail());
            statement.setString(3, student.getName());
            statement.execute();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public List<Student> getAll() {
        List<Student> lstStudent = new ArrayList<>();
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQLConfig.SELECT);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String rollName = resultSet.getString("rollName");
                String email = resultSet.getString("email");
                String name = resultSet.getString("name");
                Student student = new Student(id, rollName, email, name);
                lstStudent.add(student);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return lstStudent;
    }

    public Student getDetail(int id){
        Student obj = null;
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQLConfig.WHERE);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                String rollName = resultSet.getString("rollName");
                String email = resultSet.getString("email");
                String name = resultSet.getString("name");
                obj = new Student(id, rollName, email, name);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return obj;
    }

    public boolean editStudent(int id, Student student){
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQLConfig.UPDATE);
            statement.setString(1, student.getRollName());
            statement.setString(2, student.getEmail());
            statement.setString(3, student.getName());
            statement.setInt(4, id);
            statement.execute();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteStudent(int id){
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQLConfig.DELETE);
            statement.setInt(1, id);
            statement.execute();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
