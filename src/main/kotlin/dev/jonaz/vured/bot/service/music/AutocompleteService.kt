package dev.jonaz.vured.bot.service.music

import dev.jonaz.vured.bot.application.Translation
import dev.jonaz.vured.bot.persistence.spotify.Track
import dev.jonaz.vured.bot.service.spotify.SpotifyQueryService
import dev.jonaz.vured.bot.util.extensions.genericInject

class AutocompleteService {
    private val spotifyQueryService by genericInject<SpotifyQueryService>()

    fun autocompleteSpotify(query: String): List<String> {
        return formatSpotifyTrackList(spotifyQueryService.getTrackSearch(query))
    }

    private fun formatSpotifyTrackList(trackList: List<Track>): List<String> {
        val trackListFormatted = mutableListOf<String>()
        trackList.forEach {
            trackListFormatted.add(
                String.format(
                    Translation.AUTOCOMPLETE_SPOTIFY_TRACK,
                    it.name,
                    it.artists?.get(0)?.name
                )
            )
        }
        return trackListFormatted
    }

    fun autocompleteYoutube(query: String): List<String> {
        return autocompleteTest("TestYoutube")
    }

    fun autocompleteSpotifyAndYoutube(query: String): List<String> {
        return autocompleteTest("TestSpotifyAndYoutube")
    }

    private fun autocompleteTest(query: String): List<String> {
        print("autocompleteSpotify with query:$query")
        return arrayListOf("test1", "test2")
    }

}