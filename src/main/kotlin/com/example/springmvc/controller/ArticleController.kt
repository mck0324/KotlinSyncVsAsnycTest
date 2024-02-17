package com.example.springmvc.controller

import com.example.springmvc.model.Article
import com.example.springmvc.service.ArticleService
import com.example.springmvc.service.ReqCreate
import com.example.springmvc.service.ReqUpdate
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/article")
class ArticleController(
    private val service: ArticleService,
) {

    @GetMapping("/{id}")
    fun get(@PathVariable id:Long): Article {
        return service.get(id)
    }

    @GetMapping("/all")
    fun getAll(@RequestParam title: String?): List<Article> {
        return service.getAll(title)
    }

    @PostMapping("")
    fun create(@RequestBody request: ReqCreate): Article {
        return service.create(request)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long,@RequestBody request: ReqUpdate): Article {
        return service.update(id, request)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        return service.delete(id)
    }
}