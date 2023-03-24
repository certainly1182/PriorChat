package me.certainly1182.priorchat.listeners

import me.certainly1182.priorchat.PriorChat
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerKickEvent

class PlayerKickListener(private val plugin: PriorChat) : Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    fun onPlayerKick(event: PlayerKickEvent) {
        val kickMessage = event.leaveMessage()
        plugin.addMessageToCache(kickMessage)

        if (plugin.isDebugMode()) {
            val log = PlainTextComponentSerializer.plainText().serialize(kickMessage)
            println("[PriorChat] Caching KICK message: $log")
        }
    }
}