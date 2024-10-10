package ru.ecom.animequotes.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table


@Entity
@Table(name = "hero")
class Hero(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hero_seq")
    @JsonIgnore
    var id: Int? = null,

    @Column(nullable = false)
    var name: String? = null,

    @ManyToOne
    @JoinColumn(
        name = "anime_id",
        nullable = false
    )
    var anime: Anime? = null
)