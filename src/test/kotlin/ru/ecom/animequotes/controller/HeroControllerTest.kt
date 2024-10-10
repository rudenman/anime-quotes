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
import ru.ecom.animequotes.service.HeroService

@WebMvcTest(HeroController::class)
class HeroControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var heroService: HeroService

    @Test
    fun `updateHeroName should return OK if hero name is updated successfully`() {
        val animeName = "Naruto"
        val heroName = "Sasuke"
        val newName = "Sasuke Uchiha"
        `when`(heroService.updateHeroName(animeName, heroName, newName)).thenReturn(true)

        mockMvc.perform(
            patch("/api/v1/heroes/$animeName/$heroName")
                .content(newName)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)

        verify(heroService).updateHeroName(animeName, heroName, newName)
    }

    @Test
    fun `updateHeroName should return NOT_FOUND if hero is not found`() {
        val animeName = "Naruto"
        val heroName = "Sasuke"
        val newName = "Sasuke Uchiha"
        `when`(heroService.updateHeroName(animeName, heroName, newName)).thenReturn(false)

        mockMvc.perform(
            patch("/api/v1/heroes/$animeName/$heroName")
                .content(newName)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isNotFound)

        verify(heroService).updateHeroName(animeName, heroName, newName)
    }

    @Test
    fun `deleteHero should return OK with deleted hero count`() {
        val animeName = "Naruto"
        val heroName = "Sasuke"
        val deletedCount = 1
        `when`(heroService.deleteHero(animeName, heroName)).thenReturn(deletedCount)

        mockMvc.perform(delete("/api/v1/heroes/$animeName/$heroName"))
            .andExpect(status().isOk)
            .andExpect(content().string(deletedCount.toString()))

        verify(heroService).deleteHero(animeName, heroName)
    }
}
