package com.example.hellot2008m.model;

import com.example.hellot2008m.entity.Student;

import java.awt.*;
import java.util.HashMap;
import java.util.Scanner;

public class HashMapModel {
    static HashMap<String, Student> studentHashMap = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Menu();
    }

    public static void Menu(){
        while (true){
            System.out.println("-------------------------");
            System.out.println("Menu: ");
            System.out.println("1 - Add new student");
            System.out.println("2 - Find student by roll number");
            System.out.println("3 - Exits");
            System.out.println("-------------------------");
            System.out.println("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1:
                    addStudent();
                    break;
                case 2:
                    findStudent();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid");
                    break;
            }
            if(choice == 3){
                return;
            }
        }
    }

    private static void findStudent() {
        System.out.println("Enter roll number to find: ");
        String rollNumber = scanner.nextLine();

        if (studentHashMap.containsKey(rollNumber)){
            Student student = studentHashMap.get(rollNumber);
            System.out.println(student.toString());
        }else {
            System.err.println("Not Found");
        }
    }

    private static void addStudent() {
        System.out.println("Please enter roll number: ");
        String rollNumber = scanner.nextLine();
        System.out.println("Please enter name: ");
        String name = scanner.nextLine();
        System.out.println("Please enter email: ");
        String email = scanner.nextLine();

        Student student = new Student(rollNumber, name, email);

        studentHashMap.put(rollNumber, student);
        System.out.println("Create student success!!!");
    }
}
