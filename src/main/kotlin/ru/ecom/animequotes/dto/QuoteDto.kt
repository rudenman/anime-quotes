package ru.ecom.animequotes.dto

import com.fasterxml.jackson.annotation.JsonProperty


data class QuoteDto(
    @JsonProperty("animeName") val animeName: String,
    @JsonProperty("heroName") val heroName: String,
    @JsonProperty("quote") val content: String,
)