package me.certainly1182.priorchat.listeners

import me.certainly1182.priorchat.PriorChat
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.server.BroadcastMessageEvent

class ServerBroadcastListener(private val plugin: PriorChat) : Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    fun onServerBroadcast(event: BroadcastMessageEvent) {
        val broadcastMessage = event.message()
        plugin.addMessageToCache(broadcastMessage)

        if (plugin.isDebugMode()) {
            val log = PlainTextComponentSerializer.plainText().serialize(broadcastMessage)
            println("[PriorChat] Caching BROADCAST message: $log")
        }
    }
}