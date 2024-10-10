package ru.ecom.animequotes.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.ecom.animequotes.entity.Anime
import ru.ecom.animequotes.entity.Hero
import ru.ecom.animequotes.entity.Quote
import ru.ecom.animequotes.repository.AnimeRepository
import ru.ecom.animequotes.repository.HeroRepository
import ru.ecom.animequotes.repository.QuoteRepository

@Service
class QuoteService(
    private val animeRepository: AnimeRepository,
    private val heroRepository: HeroRepository,
    private val quoteRepository: QuoteRepository
) {

    @Transactional
    fun saveQuoteIfNotExists(animeName: String, heroName: String, quoteContent: String): Quote? {

        val anime = animeRepository.findAnimeByName(animeName) ?: Anime(name = animeName).also {
            animeRepository.save(it)
        }

        val hero = heroRepository.findHeroByNameAndAnimeId(heroName, anime.id!!)
            ?: Hero(name = heroName, anime = anime).also {
                heroRepository.save(it)
            }

        val existingQuote = quoteRepository.findQuoteByContentAndHeroId(quoteContent, hero.id!!)
        return existingQuote ?: quoteRepository.save(Quote(content = quoteContent, hero = hero))

    }

    fun getQuotesByAnime(animeName: String): List<Quote> {
        return quoteRepository.findQuotesByAnimeName(animeName)
    }

    fun getQuotesByAnimeNameAndHeroName(animeName: String, heroName: String): List<Quote> {
        return quoteRepository.findQuotesByAnimeNameAndHeroName(animeName, heroName)
    }

    fun updateQuoteContent(animeName: String, heroName: String, quoteContent: String, newContent: String): Boolean {
        return quoteRepository.updateQuoteContentByAnimeNameAndHeroNameAndContent(
            animeName,
            heroName,
            quoteContent,
            newContent
        ) != 0
    }

    fun deleteQuoteContent(animeName: String, heroName: String, quoteContent: String): Int {
        return quoteRepository.removeQuoteByAnimeNameAndHeroNameAndContent(animeName, heroName, quoteContent)
    }

}