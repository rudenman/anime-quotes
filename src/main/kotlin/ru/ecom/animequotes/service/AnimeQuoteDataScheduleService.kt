package ru.ecom.animequotes.service

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import ru.ecom.animequotes.dto.schedule.QuoteDataResponse

@Service
class AnimeQuoteDataScheduleService(
    private val quoteService: QuoteService,
    webClientBuilder: WebClient.Builder
) {

    private val webClient = webClientBuilder
        .baseUrl("https://animechan.io/api/v1")
        .build()

    @Scheduled(cron = "0 */15 * * * *")
    fun fetchQuotes() {
        Flux.range(1, 5)
            .flatMap {
                webClient.get()
                    .uri("/quotes/random")
                    .retrieve()
                    .bodyToMono(QuoteDataResponse::class.java)
            }
            .subscribe { response ->
                quoteService.saveQuoteIfNotExists(
                    response.data.anime.name,
                    response.data.character.name,
                    response.data.content
                )
            }
    }
}