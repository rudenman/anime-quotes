package ru.ecom.animequotes.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.ecom.animequotes.service.AnimeService

@RestController
@RequestMapping("api/v1/anime")
class AnimeController(
    private val animeService: AnimeService
) {

    @PatchMapping("/{animeName}")
    fun updateAnimeName(@PathVariable animeName: String, @RequestBody newName: String): ResponseEntity<Void> {
        val updatingSuccess = animeService.updateAnimeName(animeName, newName)

        if (updatingSuccess) {
            return ResponseEntity.ok().build()
        }
        return ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{animeName}")
    fun deleteAnime(@PathVariable animeName: String): ResponseEntity<Int> {
        val deletedAnimeCount = animeService.deleteAnime(animeName)
        return ResponseEntity.ok().body(deletedAnimeCount)
    }
}