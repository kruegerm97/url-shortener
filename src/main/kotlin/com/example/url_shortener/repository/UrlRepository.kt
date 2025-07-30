package com.example.url_shortener.repository

import org.springframework.data.repository.CrudRepository
import com.example.url_shortener.repository.ShortUrlEntity

interface UrlRepository : CrudRepository<ShortUrlEntity, Long> {
    fun findByHash(hash: String): ShortUrlEntity?
    fun existsByHash(hash: String): Boolean
    fun findByOriginalUrl(originalUrl: String): ShortUrlEntity?
    fun existsByOriginalUrl(originalUrl: String): Boolean
}