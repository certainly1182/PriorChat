package me.certainly1182.priorchat.listeners

import me.certainly1182.priorchat.PriorChat
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class LoginMessageHandler(private val plugin: PriorChat) : Listener {
    @EventHandler(ignoreCancelled = true)
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val player = event.player
        // send player history
        for (message in plugin.getMessageCache()) {
            player.sendMessage(message)
        }
    }
}