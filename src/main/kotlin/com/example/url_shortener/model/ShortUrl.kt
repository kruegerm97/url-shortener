package com.example.url_shortener.model

data class ShortUrl(
    val originalUrl: String,
    val hash: String
)