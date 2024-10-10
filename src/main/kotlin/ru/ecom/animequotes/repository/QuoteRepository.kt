package ru.ecom.animequotes.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional
import ru.ecom.animequotes.entity.Quote

interface QuoteRepository : JpaRepository<Quote, Int> {

    fun findQuoteByContentAndHeroId(content: String, heroId: Int): Quote?

    @Query("SELECT q FROM Quote q WHERE q.hero.anime.name = :animeName")
    fun findQuotesByAnimeName(animeName: String): List<Quote>

    @Query("SELECT q FROM Quote q WHERE q.hero.name = :heroName AND q.hero.anime.name = :animeName")
    fun findQuotesByAnimeNameAndHeroName(animeName: String, heroName: String): List<Quote>

    @Modifying
    @Transactional
    @Query("UPDATE Quote q SET q.content = :newContent WHERE q.hero.name = :heroName AND q.hero.anime.name = :animeName AND q.content = :quoteContent")
    fun updateQuoteContentByAnimeNameAndHeroNameAndContent(
        animeName: String,
        heroName: String,
        quoteContent: String,
        newContent: String
    ): Int

    @Modifying
    @Transactional
    @Query("DELETE FROM Quote q WHERE q.hero.name = :heroName AND q.hero.anime.name = :animeName AND q.content = :quoteContent")
    fun removeQuoteByAnimeNameAndHeroNameAndContent(animeName: String, heroName: String, quoteContent: String): Int
}

