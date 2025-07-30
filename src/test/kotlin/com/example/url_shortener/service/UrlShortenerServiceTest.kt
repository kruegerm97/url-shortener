package com.example.url_shortener.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import com.example.url_shortener.service.UrlShortenerService
import com.example.url_shortener.repository.UrlRepository
import com.example.url_shortener.repository.ShortUrlEntity
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class UrlShortenerServiceTest {
    private val repository = mock<UrlRepository>()
    private val service = UrlShortenerService(repository)

    @Test
    fun `shorten and resolve URL`() {
        val originalUrl = "https://www.youtube.com"

        val generatedHash = service.shortenUrl(originalUrl).hash
        val fakeEntity = ShortUrlEntity(
            hash = generatedHash,
            originalUrl = originalUrl
        )
        whenever(repository.findByHash(generatedHash)).thenReturn(fakeEntity)

        val result = service.resolveUrl(generatedHash)

        assertEquals(originalUrl, result?.originalUrl, "The resolved URL should match the original URL")
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

        val result1 = service.shortenUrl(url1)
        val result2 = service.shortenUrl(url2)

        assertNotEquals(result1.hash, result2.hash, "Different URLs should produce different hashes")
    }

    @Test
    fun `existing URL should not create a new hash`() {
        val originalUrl = "https://www.google.com"
        val existingHash = "existingHash"
        val existingEntity = ShortUrlEntity(
            hash = existingHash,
            originalUrl = originalUrl
        )

        whenever(repository.findByOriginalUrl(originalUrl)).thenReturn(existingEntity)

        val result = service.shortenUrl(originalUrl)

        assertEquals(existingHash, result.hash, "Should return the existing hash for the original URL")
    }
}