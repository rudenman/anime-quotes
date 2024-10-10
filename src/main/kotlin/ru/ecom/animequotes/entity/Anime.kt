package ru.ecom.animequotes.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table


@Entity
@Table(name = "anime")
class Anime(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "anime_seq")
    @JsonIgnore
    var id: Int? = null,

    @Column(nullable = false, unique = true)
    var name: String? = null
)