# PriorChat Plugin
[![GitHub Release](https://img.shields.io/github/v/release/certainly1182/PriorChat?include_prereleases)](https://github.com/certainly1182/PriorChat/releases)
The PriorChat plugin is a simple plugin for Minecraft [Paper](https://papermc.io) servers that stores and displays a history of chat messages for players when they join the server.

## Features
- Stores a configurable number of past chat messages in a queue.
- Displays past chat messages to players when they join the server.
- Caches join, chat, quit, and death messages.
## Installation
To install the PriorChat plugin, follow these steps:
1. Download the plugin JAR file from a reputable source.
2. Place the JAR file in the plugins folder of your Bukkit/Spigot server.
3. Start the server and verify that the plugin loaded successfully.
## Configuration
The plugin can be configured via the `config.yml` file located in `plugins/PriorChat`.

The following configuration options are available:

- `number_of_messages_to_store` (default: 50) - the maximum number of chat messages to store in the queue.
## Usage
The PriorChat plugin does not have any commands or permissions. Simply install and enable the plugin on your server to start using it.

When a player joins the server, the plugin will display a configurable number of past chat messages to the player.
