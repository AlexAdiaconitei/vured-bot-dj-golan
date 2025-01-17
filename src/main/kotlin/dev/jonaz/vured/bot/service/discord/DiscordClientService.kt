package dev.jonaz.vured.bot.service.discord

import dev.jonaz.vured.bot.listener.*
import dev.jonaz.vured.bot.service.application.ConfigService
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.utils.MemberCachePolicy
import org.slf4j.LoggerFactory
import kotlin.system.exitProcess

class DiscordClientService {
    private val logger = LoggerFactory.getLogger(this::class.java)
    private val config by ConfigService

    lateinit var jda: JDA

    fun start() = try {
        val intents = getRequiredIntents()

        jda = JDABuilder
            .createDefault(config.bot.token, intents)
            .setMemberCachePolicy(MemberCachePolicy.ALL)
            .addEventListeners(
                GuildMessageReceivedLister(),
                GuildVoiceUpdateListener(),
                MessageReactionListener(),
                ButtonClickListener(),
                SlashCommandListener(),
                AutocompleteListener()
            )
            .build()
            .awaitReady()
    } catch (e: Exception) {
        logger.error(e.message)
        exitProcess(0)
    }

    private fun getRequiredIntents() = GatewayIntent.getIntents(
        GatewayIntent.ALL_INTENTS
    ).toMutableList()
}
