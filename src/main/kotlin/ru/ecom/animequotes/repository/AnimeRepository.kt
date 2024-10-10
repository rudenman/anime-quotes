package ru.ecom.animequotes.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional
import ru.ecom.animequotes.entity.Anime

interface AnimeRepository : JpaRepository<Anime, Int> {

    fun findAnimeByName(name: String): Anime?

    @Modifying
    @Transactional
    fun removeAnimeByName(name: String): Int

    @Modifying
    @Transactional
    @Query("UPDATE Anime a SET a.name = :newName WHERE a.name = :oldName")
    fun updateAnimeNameByName(oldName: String, newName: String): Int

}