package com.example.springmvc.repository

import com.example.springmvc.model.Article
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ArticleRepository: JpaRepository<Article, Long> {
    fun findByTitleContains(title: String) : List<Article>
}