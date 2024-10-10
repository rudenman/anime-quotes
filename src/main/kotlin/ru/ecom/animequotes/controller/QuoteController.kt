package ru.ecom.animequotes.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.ecom.animequotes.dto.QuoteDto
import ru.ecom.animequotes.entity.Quote
import ru.ecom.animequotes.service.QuoteService

@RestController
@RequestMapping("/api/v1/quotes")
class QuoteController(
    private val quoteService: QuoteService
) {

    @GetMapping("/{animeName}")
    fun getQuotesByAnime(@PathVariable animeName: String): List<QuoteDto> {
        return quoteService
            .getQuotesByAnime(animeName)
            .map { quote ->
                QuoteDto(
                    animeName,
                    "all",
                    quote.content ?: "No content"
                )
            }
    }

    @GetMapping("/{animeName}/{heroName}")
    fun getQuotesByHeroAndAnime(
        @PathVariable animeName: String,
        @PathVariable heroName: String
    ): List<QuoteDto> {
        return quoteService
            .getQuotesByAnimeNameAndHeroName(animeName, heroName)
            .map { quote ->
                QuoteDto(
                    animeName,
                    heroName,
                    quote.content ?: "No content"
                )
            }
    }

    @PostMapping("")
    fun addQuote(@RequestBody quoteRequest: QuoteDto): Quote? {
        return quoteService.saveQuoteIfNotExists(quoteRequest.animeName, quoteRequest.heroName, quoteRequest.content)
    }

    @PatchMapping("/{animeName}/{heroName}/{quoteContent}")
    fun updateQuoteContentByAni(
        @PathVariable animeName: String,
        @PathVariable heroName: String,
        @PathVariable quoteContent: String,
        @RequestBody newContent: String
    ): ResponseEntity<Void> {
        val updatingSuccess = quoteService.updateQuoteContent(animeName, heroName, quoteContent, newContent)

        if (updatingSuccess) {
            return ResponseEntity.ok().build()
        }
        return ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{animeName}/{heroName}/{quoteContent}")
    fun deleteQuote(
        @PathVariable animeName: String,
        @PathVariable heroName: String,
        @PathVariable quoteContent: String,
    ): ResponseEntity<Int> {
        val deletedQuotesCount = quoteService.deleteQuoteContent(animeName, heroName, quoteContent)
        return ResponseEntity.ok().body(deletedQuotesCount)
    }
}