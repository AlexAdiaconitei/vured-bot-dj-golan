# Vured Music Bot ![CI](https://github.com/vured/vured-bot/workflows/CI/badge.svg)

<img align="right" src="https://i.imgur.com/oRkMRah.png" width=25%>

[![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://heroku.com/deploy?template=https://github.com/vured/vured-bot)

Vured is a self-hosted music bot for your discord server. 
Say goodbye to commands and hello to enjoyable ui control thanks to a static message in your music text channel.
Just send a link or search on YouTube.

![example-gif](https://i.imgur.com/uW0sJik.gif)

#### Want to import a playlist?
Just send the link to a playlist or import YouTube search results in no time.

![example-playlist-import](https://i.imgur.com/YVnwEOH.png)

### 🚢 Instant deploy with Docker

```console
docker run -d \
    --name vured-bot \
    -e BOT__TOKEN="" \
    -e DISCORD__GUILD=0 \
    -e DISCORD__MUSIC_CHANNEL=0 \
    -e DISCORD__ACCESS_ROLE=0 \
    jonaznas/vured-bot:latest
```


### 🛠 Development

You have to set the environment as a jvm argument. Choose between ``-Denv=dev`` and ``-Denv=prod``.

```
./gradlew build
```


### 📝 Config variables

Variable | Required | Description
-------- | ------------ | ------------
**BOT__TOKEN** | Yes | The discord bot token
**DISCORD__GUILD** | No* | Discord Guild ID
**DISCORD__LOG_CHANNEL** | No* | Text-Channel ID for logs
**DISCORD__MUSIC_CHANNEL** | No* | Text-Channel ID for static message
**DISCORD__ACCESS_ROLE** | No* | Role ID that can access the bot
**BOT__MAX_PLAYLIST_TRACKS** | No* | The maximum Tracks that can be in the queue
**SENTRY_DSN** | No | DSN for [Sentry](https://sentry.io)

*You can set all config variables in the [application.conf](https://github.com/vured/vured-bot/tree/master/src/main/resources) and optionally override it as an environment variable but **never put your Discord bot token into the config file**.
