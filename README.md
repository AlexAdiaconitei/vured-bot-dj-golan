<h1 align="center">Vured - A command-less music bot for Discord</h1>

<p align="center">
  <img src="https://i.imgur.com/oRkMRah.png" width="120px"/>
  <br>
  <i>
    Vured is a self-hosted music bot you can interact with by clicking buttons or using a web interface.<br>
    Say goodbye to commands and hello to enjoyable ui control.</i>
  <br>
</p>

<p align="center">
  <a href="https://github.com/vured/vured-bot/wiki">Wiki</a>
  ·
  <a href="https://vured-ui.jonaz.dev/">Web UI</a>
  <br>
  <br>
</p>

<p align="center">
    <a href="https://heroku.com/deploy?template=https://github.com/vured/vured-bot">
        <img src="https://www.herokucdn.com/deploy/button.svg" alt="heroku" />
    </a>
</p>

<hr>

Vured will be expanded in the future and new features will be added

#### Features
* [x] Static message
* [x] Discord buttons
* [x] Slash commands
* [x] Playlist import
* [x] YouTube search
* [x] Spotify search
* [x] Web UI

<br>

<img align="left" src="https://i.imgur.com/9p43DSl.png" width=39%>

<br>

<h3 align="center">A single text channel to control everything</h3>
<br>
<br>
<br>

<p align="center">
    A static message in your music text channel. Just send a link or search on YouTube.
    Change the volume or import an entire YouTube playlist with just one more click.
</p>

<br>

<img align="center" src="https://i.imgur.com/YVnwEOH.png">

<hr>

# A powerful web ui

<p>
    Some features cannot be comfortably used in a discord message.<br>
    <a href="https://github.com/vured/vured-bot/wiki/Access-web-ui">Learn more</a>
</p>

<img align="center" src="https://i.imgur.com/8uDavUf.png" >

<hr>

# Configuration
In order to get the Bot working on your Discord Server you need to manually invite it using the URL generator that you can find on the Discord Developer Portal.

Minimum permissions required code: 2150640640

You can deploy the BOT on your custom VPS using Docker or you can choose a serverless platform. More info on the [self-hosted](#Its-self-hosted) section.

### Environment variables
These are the Environment variables needed to get your Bot working.
- `BOT__TOKEN`: Your Discord Bot token. It can be acquired in the 'Bot' section after creating a 'New Application'. (Create your Discord App here  [here](https://discordapp.com/developers/applications))
- `BOT__MAX_PLAYLIST_TRACKS`: Maximum amount of tracks accepted in the queue. (Default: 100)

The following ID's can be obtained by enabling the "Developer Mode" on Discord. More info [here](https://support.discord.com/hc/en-us/articles/206346498-Where-can-I-find-my-User-Server-Message-ID-)
- `DISCORD__ACCESS_ROLE`: The ID of the Role that will have permission to execute commands and queries on the bot.
- `DISCORD__GUILD`: The ID of the Discord Server where the bot will be installed.
- `DISCORD__LOG_CHANNEL`: The ID of the text channel assigned for the bot's logs. (It is recommended to have a separate text channel for this where only moderators can view logs. For example: "vured-bot-logs")
- `DISCORD__MUSIC_CHANNEL`: The ID of the text channel where the BOT will stick its static message with the control buttons. This channel will also be listening for your URL inputs of tracks. <br>
  ⚠️IMPORTANT⚠️The bot will delete all messages on this channel if it contains any. It is recommended that you create a new channel for this. For example: "vured-bot"


Spotify Environment variables are optional. Only required if you wish to use Spotify features. To get a Spotify clientId & clientSecret you must go [here](https://developer.spotify.com/dashboard/login) and create a new application:
- `SPOTIFY__CLIENT_ID`: Your Spotify clientId.
- `SPOTIFY__CLIENT_SECRET`: Your Spotify clientSecret.
- `SPOTIFY__COUNTRY_CODE`: The country code you want to use for filtering the artists top tracks.

# It's self-hosted
Thanks to modern serverless platforms, however, it is easy and cheap or free of charge.<br>
Here are some services to deploy it.

- [Heroku](https://heroku.com/)
- [Fly](https://fly.io/)
- [Replit](https://replit.com/)
- [AppEngine](https://cloud.google.com/appengine)

### 🚢 Instant deploy with Docker
Get your bot working with Docker by copy/paste this command. Don't forget to update your env variables.
```bash
docker run -d \
    --name vured-bot \
    -e BOT__TOKEN="" \
    -e DISCORD__GUILD=0 \
    -e DISCORD__ACCESS_ROLE=0 \
    -e DISCORD__GUILD=0 \
    -e DISCORD__LOG_CHANNEL=0 \
    -e DISCORD__MUSIC_CHANNEL=0 \
    jonaznas/vured-bot:latest
```
### Docker Compose example

If you want to get this Fork image you can deploy it with this compose file (Choose your `tag` from the [releases](https://github.com/AlexAdiaconitei/vured-bot-dj-golan/releases) page).
```yaml
version: '3.3'
services:
  vured-bot:
    image: 'alexadiaconitei/vured-bot-dj-golan:<tag>'
    container_name: dj-golan
    environment:
      BOT__TOKEN: "Your Discord Bot Token"
      DISCORD__ACCESS_ROLE: Your Access Role ID
      DISCORD__GUILD: Your Discord Server ID
      DISCORD__LOG_CHANNEL: Your Log Channel ID
      DISCORD__MUSIC_CHANNEL: Your Music Channel ID
      BOT__MAX_PLAYLIST_TRACKS: 100
      SPOTIFY__CLIENT_ID: Your Spotify Client ID (If required)
      SPOTIFY__CLIENT_SECRET: Your Spotify Client Secret (If required)
      SPOTIFY__COUNTRY_CODE: US (Change to your preference)
```
# Contributors

<a href = "https://github.com/vured/vured-bot/graphs/contributors">
  <img src = "https://contrib.rocks/image?repo=vured/vured-bot"/>
</a>
