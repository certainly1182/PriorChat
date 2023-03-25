# PriorChat Plugin
[![GitHub Release](https://img.shields.io/github/v/release/certainly1182/PriorChat?include_prereleases)](https://github.com/certainly1182/PriorChat/releases)
[![Modrinth Downloads](https://img.shields.io/modrinth/dt/JMSHw1n4)](https://modrinth.com/plugin/priorchat)

Simple plugin for Minecraft [Paper](https://papermc.io) servers that stores and displays a history of chat messages for players when they join the server.

## Features
- Stores a configurable number of past chat messages in a queue.
- Displays past chat messages to players when they join the server.
- Stores join, chat, quit, death, broadcast, advancement and kick messages.
## Installation
To install PriorChat, follow these steps:
1. Download the plugin JAR file from [Modrinth](https://modrinth.com/plugin/priorchat) or the [Releases](https://github.com/certainly1182/PriorChat/releases) page.
2. Place the JAR file in the plugins folder of your Paper (or Paper fork) server.
3. Start the server and verify that the plugin loaded successfully.
## Configuration
The plugin can be configured via the `config.yml` file located in `plugins/PriorChat`.

The following configuration options are available:
- `debug_mode` (default: false) - Debug mode toggle.
- `number_of_messages_to_store` (default: 50) - The maximum number of chat messages to store in the queue.
- `enabled_message_types` (default: all types) - List of message types to store. Valid values are `CHAT`, `JOIN`, `QUIT`, `DEATH`, `BROADCAST`, `ADVANCEMENT` and `KICK`.
### Default Config
```yml
debug_mode: false
number_of_messages_to_store: 50
enabled_message_types:
  - CHAT
  - JOIN
  - QUIT
  - DEATH
  - BROADCAST
  - ADVANCEMENT
  - KICK
  ```
## Usage
The PriorChat plugin does not *yet* have any commands or permissions. Simply install the plugin on your server to start using it.

When a player joins the server, the plugin will display a configurable number of past chat messages to the player.
