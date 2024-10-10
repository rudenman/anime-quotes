package ru.ecom.animequotes.service

import org.springframework.stereotype.Service
import ru.ecom.animequotes.repository.AnimeRepository

@Service
class AnimeService(
    private val animeRepository: AnimeRepository,
) {

    fun deleteAnime(animeName: String): Int {
        return animeRepository.removeAnimeByName(animeName)
    }

    fun updateAnimeName(animeName: String, newName: String): Boolean {
        return animeRepository.updateAnimeNameByName(animeName, newName) != 0
    }


}