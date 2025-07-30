package com.example.url_shortener.controller

import com.example.url_shortener.service.UrlShortenerService
import com.example.url_shortener.controller.dto.UrlShortenRequest
import com.example.url_shortener.controller.dto.UrlShortenResponse
import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity
import jakarta.validation.Valid

@RestController
@RequestMapping("/api")
class UrlShortenerController(
    private val service: UrlShortenerService
) {
    @PostMapping("/shorten")
    fun shortenUrl(@RequestBody @Valid request: UrlShortenRequest): ResponseEntity<UrlShortenResponse> {
        val result = service.shortenUrl(request.url)
        return ResponseEntity.ok(UrlShortenResponse(result.hash))
    }

    @GetMapping("/{hash}")
    fun resolveUrl(@PathVariable hash: String): ResponseEntity<String> {
        val result = service.resolveUrl(hash)
        return result?.let {
            ResponseEntity.ok(it.originalUrl)
        } ?: ResponseEntity.notFound().build()
    }
}
