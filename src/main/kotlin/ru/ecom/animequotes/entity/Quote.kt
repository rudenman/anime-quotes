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
@Table(name = "quote")
class Quote(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quote_seq")
    @JsonIgnore
    var id: Int? = null,

    @Column(nullable = false)
    var content: String? = null,

    @ManyToOne
    @JoinColumn(
        name = "hero_id",
        nullable = false
    )
    var hero: Hero? = null
)