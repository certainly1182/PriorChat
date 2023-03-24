package me.certainly1182.priorchat.listeners

import me.certainly1182.priorchat.PriorChat
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent

class PlayerDeathListener(private val plugin: PriorChat) : Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    fun onPlayerDeath(event: PlayerDeathEvent) {
        val deathMessage = event.deathMessage() ?: return
        plugin.addMessageToCache(deathMessage)

        if (plugin.isDebugMode()) {
            val log = PlainTextComponentSerializer.plainText().serialize(deathMessage)
            println("[PriorChat] Caching DEATH message: $log")
        }
    }
}