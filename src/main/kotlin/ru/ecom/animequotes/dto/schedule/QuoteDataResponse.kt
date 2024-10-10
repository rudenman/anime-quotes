package ru.ecom.animequotes.dto.schedule

import com.fasterxml.jackson.annotation.JsonProperty

data class QuoteDataResponse(
    @JsonProperty("status") val status: String,
    @JsonProperty("data") val data: QuoteData
) {
    data class QuoteData(
        @JsonProperty("content") val content: String,
        @JsonProperty("anime") val anime: Anime,
        @JsonProperty("character") val character: Character
    ) {
        data class Anime(
            @JsonProperty("id") val id: Int,
            @JsonProperty("name") val name: String
        )

        data class Character(
            @JsonProperty("id") val id: Int,
            @JsonProperty("name") val name: String
        )
    }
}