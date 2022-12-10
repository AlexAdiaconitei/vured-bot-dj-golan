package dev.jonaz.vured.bot.service.youtube

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class YoutubeSearchService {

    companion object {
        const val API_BASE = "https://suggestqueries.google.com/complete/"
    }

    private val mapper = jacksonObjectMapper()

    fun getSearch(query: String): List<String> {
        val json = this.getJson(
            API_BASE + "search?client=firefox&ds=yt&q="
                + URLEncoder.encode(query, StandardCharsets.UTF_8)
        )
        var resultList: List<String> = emptyList()
        json?.get(1)?.let {
            val res = it
            resultList = mapper.readValue(res.toString())
        }
        return resultList
    }

    private fun getJson(uri: String?): JsonNode? {
        val httpClient: CloseableHttpClient = HttpClients.createDefault()
        val request = HttpGet(uri)
        val response: CloseableHttpResponse = httpClient.execute(request)
        val json = mapper.readTree(EntityUtils.toString(response.entity, StandardCharsets.UTF_8))
        httpClient.close()
        return json
    }

}