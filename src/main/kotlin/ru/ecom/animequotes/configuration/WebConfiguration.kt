package ru.ecom.animequotes.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient


@Configuration
class WebConfiguration {

    @Bean
    fun clientBuilder(): WebClient.Builder {
        return WebClient.builder()
    }
}