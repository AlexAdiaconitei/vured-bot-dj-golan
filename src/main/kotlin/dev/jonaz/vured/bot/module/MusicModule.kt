package dev.jonaz.vured.bot.module

import dev.jonaz.vured.bot.service.music.MusicService
import dev.jonaz.vured.bot.service.music.PlaylistService
import dev.jonaz.vured.bot.service.spotify.SpotifyQueryService
import org.koin.dsl.module

val musicModule = module {
    single { MusicService() }
    single { PlaylistService() }
    single { SpotifyQueryService() }
}
