package com.example.springmvc.service

import com.example.springmvc.model.Article
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@ExtendWith(MockitoExtension::class)
@Transactional
@Rollback
class ArticleServiceTest(
    @Autowired private val service: ArticleService,
//    @Mock private val service: ArticleService,
) {
    @Test
    fun `create and get`() {
//        Mockito.`when`(service.get(1)).thenReturn(
//            Article(1,"title","blabla",1234)
//        )
        val article = service.create(ReqCreate(
            title = "title",
            body = "blabla",
            authorId = 1234
        )). let { service.get(it.id) }


        assertEquals("title",article.title)
        assertEquals("blabla",article.body)
        assertEquals(1234,article.authorId)
    }

    @Test
    fun getAll() {
        repeat(5) {i ->
            service.create(ReqCreate(
                title = "title $i",
                body = "blabla $i",
                authorId = 1234
            ))
        }

        assertTrue(service.getAll().size >= 5)

    }

    @Test
    fun update() {
        val create = service.create(ReqCreate(
            title = "title",
            body = "blabla",
            authorId = 1234
        ))
        service.update(create.id, ReqUpdate(title = "updatedTitle"))
        val updated = service.get(create.id)
        assertEquals("updatedTitle",updated.title)


    }@Test
    fun delete() {
        repeat(5) { i ->
            service.create(ReqCreate(
                title = "title $i",
                body = "body $i",
                authorId = 1234
            ))
        }
        val prev = service.getAll().size
        service.delete(1)
        val after = service.getAll().size
        assertEquals(prev - 1,after)

    }


}