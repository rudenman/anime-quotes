package ru.ecom.animequotes.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional
import ru.ecom.animequotes.entity.Hero

interface HeroRepository : JpaRepository<Hero, Int> {

    fun findHeroByNameAndAnimeId(name: String, animeId: Int): Hero?

    @Modifying
    @Transactional
    @Query("UPDATE Hero h SET h.name = :newName WHERE h.name = :oldName AND h.anime.name = :animeName")
    fun updateHeroNameByAnimeNameAndName(animeName: String, oldName: String, newName: String): Int

    @Modifying
    @Transactional
    fun removeHeroByAnimeNameAndName(animeName: String, heroName: String): Int
}