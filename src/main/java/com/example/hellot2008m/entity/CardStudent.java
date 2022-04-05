package com.example.hellot2008m.entity;

import com.example.hellot2008m.annotation.Column;
import com.example.hellot2008m.annotation.ForeignKey;
import com.example.hellot2008m.annotation.Id;
import com.example.hellot2008m.annotation.Table;

@Table(name = "card_students")
public class CardStudent {
    @Id(autoIncrement = true)
    @Column(name = "id", type = "int")
    private int id;
    @Column(name = "name", type = "varchar(255)")
    private String name;
    @Column(name = "code", type = "varchar(250)")
    private String code;
    @Column(name = "studentId", type = "int")
    @ForeignKey(referenceTable = "students", referenceColumn = "id")
    private int studentId;

    public CardStudent() {
    }

    public CardStudent(int id, String name, String code, int studentId) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.studentId = studentId;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}
