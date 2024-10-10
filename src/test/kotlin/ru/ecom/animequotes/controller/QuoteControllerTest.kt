package ru.ecom.animequotes.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.reset
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import ru.ecom.animequotes.dto.QuoteDto
import ru.ecom.animequotes.service.QuoteService

@WebMvcTest(QuoteController::class)
class QuoteControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockBean
    lateinit var quoteService: QuoteService

    @Autowired
    lateinit var objectMapper: ObjectMapper


    private lateinit var quoteDto: QuoteDto


    private val animeName = "MyAnime"
    private val heroName = "Hero"
    private val quoteContent = "This is a quote"

    @BeforeEach
    fun setup() {
        reset(quoteService)
    }

    @Test
    fun `should delete a quote`() {
        `when`(quoteService.deleteQuoteContent(animeName, heroName, quoteContent)).thenReturn(1)

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/quotes/$animeName/$heroName/$quoteContent"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string("1"))
    }
}
