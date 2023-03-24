package me.certainly1182.priorchat.listeners

import io.papermc.paper.event.player.AsyncChatEvent
import me.certainly1182.priorchat.PriorChat
import net.kyori.adventure.audience.Audience
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener

class PlayerChatListener(private val plugin: PriorChat) : Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    fun onPlayerChat(event: AsyncChatEvent) {
        val player = event.player
        val chatMessage = event.message()
        val renderer = event.renderer()
        val rendered = renderer.render(player, player.displayName(), chatMessage, Audience.empty())
        plugin.addMessageToCache(rendered)

        if (plugin.isDebugMode()) {
            val log = PlainTextComponentSerializer.plainText().serialize(rendered)
            println("[PriorChat] Caching CHAT message: $log")
        }
    }
}