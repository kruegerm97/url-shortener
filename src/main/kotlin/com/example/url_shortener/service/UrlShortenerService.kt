package com.example.url_shortener.service

import org.springframework.stereotype.Service
import org.hashids.Hashids
import com.example.url_shortener.model.ShortUrl
import com.example.url_shortener.repository.UrlRepository

@Service
class UrlShortenerService(
    private val repository: UrlRepository
) {
    private val hashids = Hashids("url-shortener-salt", 8)

    fun shortenUrl(originalUrl: String): ShortUrl {
        repository.findByOriginalUrl(originalUrl)?.let {
            return ShortUrl(it.originalUrl, it.hash)
        }

        val hash = hashids.encode(originalUrl.hashCode().toLong())
        return ShortUrl(originalUrl, hash)
    }

    fun resolveUrl(hash: String): ShortUrl? {
        return repository.findByHash(hash)?.let {
            ShortUrl(it.originalUrl, it.hash)
        }
    }
}