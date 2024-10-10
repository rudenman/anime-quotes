package ru.ecom.animequotes.service

import org.springframework.stereotype.Service
import ru.ecom.animequotes.repository.HeroRepository

@Service
class HeroService(
    private val heroRepository: HeroRepository
) {
    fun updateHeroName(animeName: String, heroName: String, newName: String): Boolean {
        return heroRepository.updateHeroNameByAnimeNameAndName(animeName, heroName, newName) != 0
    }

    fun deleteHero(animeName: String, heroName: String): Int {
        return heroRepository.removeHeroByAnimeNameAndName(animeName, heroName)
    }


}