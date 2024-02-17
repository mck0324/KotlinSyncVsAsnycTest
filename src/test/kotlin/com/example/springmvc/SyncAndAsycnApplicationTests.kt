package com.example.springmvc

import com.example.springmvc.model.Article
import com.example.springmvc.repository.ArticleRepository
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import mu.KotlinLogging
import org.junit.jupiter.api.Assertions.assertEquals
import org.springframework.beans.factory.annotation.Autowired

private val logger = KotlinLogging.logger {  }

@SpringBootTest
class SyncAndAsycnApplicationTests(
    @Autowired private val repository: ArticleRepository,
) {

    @Test
    fun contextLoads() {
        val prev = repository.count()
        repository.save(
            Article(
                id = 1,
                title = "title",
                body = "body",
                authorId = 1234,
            )
        ).let { logger.debug { it } }
        val curr = repository.count()
        logger.debug { ">>prev: $prev, curr: $curr" }
        assertEquals(prev + 1, curr)
    }

}
