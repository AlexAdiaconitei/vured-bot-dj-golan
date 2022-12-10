package dev.jonaz.vured.bot.service.spotify

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import dev.jonaz.vured.bot.persistence.autocomplete.Album
import dev.jonaz.vured.bot.persistence.autocomplete.Track
import dev.jonaz.vured.bot.service.application.ConfigService
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.apache.http.message.BasicNameValuePair
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.time.Instant
import java.util.*


class SpotifySearchService {
    private val config by ConfigService

    companion object {
        const val API_BASE = "https://api.spotify.com/v1/"
    }

    private var token: String? = null
    private var tokenExpire: Instant? = null
    private val mapper = jacksonObjectMapper()

    fun getTrackSearch(query: String): List<Track> {
        val json = this.getJson(API_BASE + "search?q="
                + URLEncoder.encode(query, StandardCharsets.UTF_8)
                + "&type=track&limit=10")
        var trackList: List<Track> = emptyList()
        json?.get("tracks")?.get("items")?.let {
            val res = it
            trackList = mapper.readValue(res.toString())
        }
        trackList = trackList.distinctBy { it.name }
        return trackList
    }

    fun getAlbumSearch(query: String): List<Album> {
        val json = this.getJson(API_BASE + "search?q="
                + URLEncoder.encode(query, StandardCharsets.UTF_8)
                + "&type=album&limit=10")
        var albumList: List<Album> = emptyList()
        json?.get("albums")?.get("items")?.let {
            val res = it
            albumList = mapper.readValue(res.toString())
        }
        albumList = albumList.distinctBy { it.name }
        return albumList
    }

    private fun getJson(uri: String?): JsonNode? {
        val httpClient: CloseableHttpClient = HttpClients.createDefault()
        val request = HttpGet(uri)
        request.addHeader("Authorization", "Bearer ${this.getToken()}")
        val response: CloseableHttpResponse  = httpClient.execute(request)
        val json = mapper.readTree(response.entity.content)
        httpClient.close()
        return json
    }

    private fun getToken(): String? {
        if (this.token == null || this.tokenExpire == null || this.tokenExpire!!.isBefore(Instant.now())) {
            this.requestToken()
        }
        return this.token
    }

    private fun requestToken() {
        val httpClient: CloseableHttpClient = HttpClients.createDefault()
        val request = HttpPost("https://accounts.spotify.com/api/token")
        request.addHeader(
            "Authorization",
            "Basic " + Base64.getEncoder()
                .encodeToString((config.spotify?.clientId + ":" + config.spotify?.clientSecret)
                    .toByteArray(StandardCharsets.UTF_8))
        )
        request.entity = UrlEncodedFormEntity(
            listOf(
                BasicNameValuePair(
                    "grant_type",
                    "client_credentials"
                )
            ), StandardCharsets.UTF_8
        )
        val response: CloseableHttpResponse  = httpClient.execute(request)
        val json = mapper.readTree(response.entity.content)
        if (json != null) {
            token = json.get("access_token").asText()
            tokenExpire = Instant.now().plusSeconds(json.get("expires_in").asLong(0))
        }
        httpClient.close()
    }

}