package ru.ecom.animequotes.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.ecom.animequotes.service.HeroService

@RestController
@RequestMapping("api/v1/heroes")
class HeroController(
    private val heroService: HeroService
) {

    @PatchMapping("/{animeName}/{heroName}")
    fun updateHeroName(
        @PathVariable animeName: String,
        @PathVariable heroName: String,
        @RequestBody newName: String
    ): ResponseEntity<Void> {
        val updatingSuccess = heroService.updateHeroName(animeName, heroName, newName)

        if (updatingSuccess) {
            return ResponseEntity.ok().build()
        }
        return ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{animeName}/{heroName}")
    fun deleteHero(
        @PathVariable animeName: String,
        @PathVariable heroName: String
    ): ResponseEntity<Int> {
        val deletedHeroCount = heroService.deleteHero(animeName, heroName)
        return ResponseEntity.ok().body(deletedHeroCount)
    }

}