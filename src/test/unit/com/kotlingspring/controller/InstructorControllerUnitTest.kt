package com.kotlingspring.controller

import com.kotlinspring.controller.CourseController
import com.kotlinspring.dto.CourseDTO
import com.kotlinspring.dto.InstructorDTO
import com.kotlinspring.service.InstructorService
import com.kotlinspring.util.instructorDTO
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.reactive.server.WebTestClient

@WebMvcTest(controllers = [CourseController::class])
@AutoConfigureWebTestClient
class InstructorControllerUnitTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockkBean
    lateinit var instructorMockk: InstructorService

    @Test
    fun addInstructor() {
        val instructorDTO = InstructorDTO(null, "Nilgun Demirkaya")

        every { instructorMockk.addInstructor(any()) } returns instructorDTO(1, "Nilgun Demirkaya")

        val savedInstructorDTO =  webTestClient
            .post()
            .uri("/v1/instructors")
            .bodyValue(instructorDTO)
            .exchange()
            .expectStatus().isCreated
            .expectBody(InstructorDTO::class.java)
            .returnResult()
            .responseBody

        Assertions.assertTrue{
            savedInstructorDTO!!.id != null
        }
    }

    @Test
    fun addInstructor_validation() {
        val instructorDTO = InstructorDTO(null, "")

        every { instructorMockk.addInstructor(any()) } returns instructorDTO(1, "Nilgun Demirkaya")

        //when
        val response =  webTestClient
            .post()
            .uri("/v1/instructors")
            .bodyValue(instructorDTO)
            .exchange()
            .expectStatus().isBadRequest
            .expectBody(String::class.java)
            .returnResult()
            .responseBody

        //then
        Assertions.assertEquals("instructorDTO.name must not be blank", response)
    }
}