package dev.jonaz.vured.bot.persistence.spotify

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable

@Serializable
@JsonIgnoreProperties(ignoreUnknown = true)
data class Track (
    @JsonProperty("artists")
    val artists: List<Artist>?,
    @JsonProperty("name")
    val name: String?,
)

@Serializable
@JsonIgnoreProperties(ignoreUnknown = true)
data class Artist (
    @JsonProperty("name")
    val name: String?
)
