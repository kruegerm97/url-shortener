package com.example.url_shortener.service

import org.springframework.stereotype.Service
import org.hashids.Hashids
import com.example.url_shortener.model.ShortUrl

@Service
class UrlShortenerService() {
    private val hashids = Hashids("url-shortener-salt", 8)

    fun shortenUrl(originalUrl: String): ShortUrl {
        val hash = hashids.encode(originalUrl.hashCode().toLong())
        return ShortUrl(originalUrl, hash)
    }

    fun resolveUrl(hash: String): ShortUrl? {
        return null
    }
}