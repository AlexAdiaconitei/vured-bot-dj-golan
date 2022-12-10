package dev.jonaz.vured.bot.listener

import dev.jonaz.vured.bot.service.music.AutocompleteService
import dev.jonaz.vured.bot.util.extensions.genericInject
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class AutocompleteListener: ListenerAdapter() {
    private val autocompleteService by genericInject<AutocompleteService>()

    override fun onCommandAutoCompleteInteraction(event: CommandAutoCompleteInteractionEvent) {
        print("event:$event.name")
        if (event.name == "spotify" && event.focusedOption.name == "query") {
            event.replyChoiceStrings(
                autocompleteService.autocompleteSpotify(event.focusedOption.value)
            ).queue()
        }
    }
}