package com.example.url_shortener.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import com.example.url_shortener.service.UrlShortenerService

class UrlShortenerServiceTest {

    private val service = UrlShortenerService()

    @Test
    fun `shorten and resolve URL`() {
        val originalUrl = "https://www.youtube.com"
        val hash = service.shortenUrl(originalUrl)
        val resolvedUrl = service.resolveUrl(hash)

        assertEquals(originalUrl, resolvedUrl, "The resolved URL should match the original URL")
    }

    @Test
    fun `resolving unknown hash should return null`() {
        val result = service.resolveUrl("unknownHash")
        assertNull(result)
    }

    @Test
    fun `different URLs should produce different hashes`() {
        val url1 = "https://www.google.com"
        val url2 = "https://www.github.com"

        val hash1 = service.shortenUrl(url1)
        val hash2 = service.shortenUrl(url2)

        assertNotEquals(hash1, hash2, "Different URLs should produce different hashes")
    }
}