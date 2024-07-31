package com.kotlinspring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CourseCatalogServiceApplication

fun main(args: Array<String>) {
	runApplication<CourseCatalogServiceApplication>(*args)

	val courseJava = CourseJava(2,
		"",
		"")

	println("courseJava :$courseJava")
	courseJava.id = 3
	courseJava.name = ""
	courseJava.category = ""
	println("courseJava :$courseJava")
}
