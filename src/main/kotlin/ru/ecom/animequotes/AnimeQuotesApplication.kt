package ru.ecom.animequotes

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class AnimeQuotesApplication

fun main(args: Array<String>) {
    runApplication<AnimeQuotesApplication>(*args)
}
