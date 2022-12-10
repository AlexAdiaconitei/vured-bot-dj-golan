package dev.jonaz.vured.bot.service.music

import dev.jonaz.vured.bot.application.Translation
import dev.jonaz.vured.bot.persistence.autocomplete.Album
import dev.jonaz.vured.bot.persistence.autocomplete.Track
import dev.jonaz.vured.bot.service.spotify.SpotifySearchService
import dev.jonaz.vured.bot.service.youtube.YoutubeSearchService
import dev.jonaz.vured.bot.util.extensions.genericInject

class AutocompleteService {
    private val spotifySearchService by genericInject<SpotifySearchService>()
    private val youtubeSearchService by genericInject<YoutubeSearchService>()

    fun autocompleteSpotify(query: String): List<String> {
        val trackList: List<Track> = spotifySearchService.getTrackSearch(query)
        val albumList: List<Album> = spotifySearchService.getAlbumSearch(query)
        return mergeResultsList(trackList.take(8), albumList.take(2), emptyList())
    }

    private fun mergeResultsList(spotifyTrackList: List<Track>,
                                 spotifyAlbumList: List<Album>,
                                 youtubeList: List<String>,
    ): List<String> {
        return formatSpotifyTrackList(spotifyTrackList) + formatSpotifyAlbumList(spotifyAlbumList) + formatYoutubeList(youtubeList)
    }

    private fun formatSpotifyTrackList(trackList: List<Track>): List<String> {
        val trackListFormatted = mutableListOf<String>()
        trackList.forEach {
            trackListFormatted.add(
                String.format(
                    Translation.AUTOCOMPLETE_SPOTIFY_TRACK,
                    it.name,
                    it.artists?.get(0)?.name
                ).take(100)
            )
        }
        return trackListFormatted
    }

    private fun formatSpotifyAlbumList(albumList: List<Album>): List<String> {
        val albumListFormatted = mutableListOf<String>()
        albumList.forEach {
            albumListFormatted.add(
                String.format(
                    Translation.AUTOCOMPLETE_SPOTIFY_ALBUM,
                    it.name,
                    it.artists?.get(0)?.name
                ).take(100)
            )
        }
        return albumListFormatted
    }

    fun autocompleteYoutube(query: String): List<String> {
        return formatYoutubeList(youtubeSearchService.getSearch(query))
    }

    private fun formatYoutubeList(resultList: List<String>): List<String> {
        val resultListFormatted = mutableListOf<String>()
        resultList.forEach {
            resultListFormatted.add(
                String.format(
                    Translation.AUTOCOMPLETE_YOUTUBE,
                    it
                ).take(100)
            )
        }
        return resultListFormatted
    }

    fun autocompleteSpotifyAndYoutube(query: String): List<String> {
        val trackList: List<Track> = spotifySearchService.getTrackSearch(query)
        val albumList: List<Album> = spotifySearchService.getAlbumSearch(query)
        val ytList: List<String> = youtubeSearchService.getSearch(query)
        return mergeResultsList(trackList.take(4), albumList.take(2), ytList.take(4))
    }

}