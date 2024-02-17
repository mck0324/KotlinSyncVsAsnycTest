package com.example.springmvc.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @GetMapping("/hello")
    fun hello(@RequestParam name: String?): String {
        return "Hello $name ~"
    }

    @GetMapping("/hello/{name}")
    fun hellow(@PathVariable name: String?): String {
        return "Hello $name~~~"
    }
}