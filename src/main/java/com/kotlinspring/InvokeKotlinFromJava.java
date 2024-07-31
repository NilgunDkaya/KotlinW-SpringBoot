package com.kotlinspring;

import com.kotlinspring.entity.Course;
import com.kotlinspring.entity.Instructor;

import java.util.List;

public class InvokeKotlinFromJava {
    public static void main(String[] args) {

        var course = new Course(1,
                "Kotlin",
                "Nilgun",
                new Instructor(1, "Nilgun", List.of()));

        System.out.println("course : " + course);
    }
}
