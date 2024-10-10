package ru.ecom.animequotes.controller

import org.junit.jupiter.api.Test
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import ru.ecom.animequotes.service.AnimeService

@WebMvcTest(AnimeController::class)
class AnimeControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var animeService: AnimeService

    @Test
    fun `updateAnimeName should return OK if anime name is updated successfully`() {
        val animeName = "Naruto"
        val newName = "Naruto Shippuden"
        `when`(animeService.updateAnimeName(animeName, newName)).thenReturn(true)

        mockMvc.perform(
            patch("/api/v1/anime/$animeName")
                .content(newName)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)

        verify(animeService).updateAnimeName(animeName, newName)
    }

    @Test
    fun `updateAnimeName should return NOT_FOUND if anime is not found`() {
        val animeName = "Naruto"
        val newName = "Naruto Shippuden"
        `when`(animeService.updateAnimeName(animeName, newName)).thenReturn(false)

        mockMvc.perform(
            patch("/api/v1/anime/$animeName")
                .content(newName)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isNotFound)

        verify(animeService).updateAnimeName(animeName, newName)
    }

    @Test
    fun `deleteAnime should return OK with deleted anime count`() {
        val animeName = "Naruto"
        val deletedCount = 1
        `when`(animeService.deleteAnime(animeName)).thenReturn(deletedCount)

        mockMvc.perform(delete("/api/v1/anime/$animeName"))
            .andExpect(status().isOk)
            .andExpect(content().string(deletedCount.toString()))

        verify(animeService).deleteAnime(animeName)
    }
}
