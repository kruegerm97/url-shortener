package com.example.url_shortener.repository

import jakarta.persistence.*

@Entity
@Table(name = "short_urls")
data class ShortUrlEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(name = "original_url", nullable = false)
    val originalUrl: String,
    @Column(name = "hash", nullable = false, unique = true)
    val hash: String
)