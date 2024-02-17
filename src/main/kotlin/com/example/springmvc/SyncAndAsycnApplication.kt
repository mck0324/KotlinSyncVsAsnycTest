package com.example.springmvc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class SyncAndAsycnApplication

fun main(args: Array<String>) {
    runApplication<SyncAndAsycnApplication>(*args)
}
