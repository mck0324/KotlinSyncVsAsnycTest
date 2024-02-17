package com.example.springmvc.controller

import com.example.springmvc.service.ArticleService
import com.example.springmvc.service.ReqCreate
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.put

@SpringBootTest
@AutoConfigureMockMvc
@Sql("classpath:db-init/test.sql")
class ArticleControllerTest(
    @Autowired private val mockMvc: MockMvc,
    @Autowired private val articleService: ArticleService
) {

    @Test
    fun get() {
        mockMvc.get("/article/1") {
            contentType = APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            jsonPath("title") { value("title1")}
        }
    }

    @Test
    fun getAll() {
        mockMvc.get("/article/all") {
            contentType = APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            jsonPath("$.length()") { value(1) }
        }

    }

    @Test
    fun create() {
        mockMvc.post("/article") {
            contentType = APPLICATION_JSON
            content = """
                {
                    "title": "title2",
                    "body": "body2",
                    "authorId": 1234
                }
            """.trimIndent()
        }.andExpect {
            status { isOk() }
            jsonPath("title") {value("title2")}
            jsonPath("body") {value("body2")}
        }
    }

    @Test
    fun update() {
        mockMvc.put("/article/1") {
            contentType = APPLICATION_JSON
            content = """
                {
                    "title": "title update",
                    "body": "body update",
                    "authorId": 4444
                }
            """.trimIndent()
        }.andExpect {
            status { isOk() }
            jsonPath("title") { value("title update") }
            jsonPath("body") { value("body update") }
        }

    }

    @Test
    fun delete() {
        val create = articleService.create(ReqCreate(
            title = "titlehi",
            body = "bodyhi",
            authorId = 1212
        ))
        val prev = articleService.getAll().size

        mockMvc.delete("/article/${create.id}") {
            contentType = APPLICATION_JSON
        }.andExpect {
            status { isOk() }
        }
        val after = articleService.getAll().size
        assertEquals(prev - 1,after)
    }
}