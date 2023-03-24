package me.certainly1182.priorchat.listeners

import me.certainly1182.priorchat.PriorChat
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerJoinListener(private val plugin: PriorChat) : Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val joinMessage = event.joinMessage() ?: return
        plugin.addMessageToCache(joinMessage)

        if (plugin.isDebugMode()) {
            val log = PlainTextComponentSerializer.plainText().serialize(joinMessage)
            println("[PriorChat] Caching JOIN message: $log")
        }
    }
}