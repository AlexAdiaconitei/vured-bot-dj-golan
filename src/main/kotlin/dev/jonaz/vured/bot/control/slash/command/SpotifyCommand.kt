package dev.jonaz.vured.bot.control.slash.command

import dev.jonaz.vured.bot.application.Translation
import dev.jonaz.vured.bot.control.slash.CommandHandler
import dev.jonaz.vured.bot.control.slash.SlashCommand
import dev.jonaz.vured.bot.service.music.MusicService
import dev.jonaz.vured.bot.util.extensions.genericInject
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.requests.restaction.CommandCreateAction

@SlashCommand(
    name = "spotify",
    description = Translation.COMMAND_SPOTIFY_DESC
)
class SpotifyCommand : CommandHandler {
    private val musicService by genericInject<MusicService>()

    override fun upsertCommand(commandCreateAction: CommandCreateAction): CommandCreateAction {
        return commandCreateAction.addOption(
            OptionType.STRING,
            Translation.COMMAND_SPOTIFY_OPTION_NAME,
            Translation.COMMAND_SPOTIFY_OPTION_DESC,
            true,
            true
        )
    }

    override fun execute(event: SlashCommandInteractionEvent) {
        var query = event.getOption("query")?.asString
            ?.replace(Translation.AUTOCOMPLETE_SPOTIFY_TRACK_ICON, "")
            ?.replace(Translation.AUTOCOMPLETE_SPOTIFY_ALBUM_ICON, "")

        query?.run {
            musicService.loadItem(event.member, "spsearch:$this")
        }

        event.hook.sendMessage(Translation.COMMAND_SPOTIFY_RESPONSE).setEphemeral(true).queue()
    }
}
