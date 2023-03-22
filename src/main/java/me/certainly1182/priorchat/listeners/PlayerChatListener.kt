package me.certainly1182.priorchat.listeners

import io.papermc.paper.event.player.AsyncChatEvent
import me.certainly1182.priorchat.PriorChat
import net.kyori.adventure.audience.Audience
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class PlayerChatListener(private val plugin: PriorChat) : Listener {
    @EventHandler(ignoreCancelled = true)
    fun onPlayerChat(event: AsyncChatEvent) {
        val player = event.player
        val chatMessage = event.message()
        val renderer = event.renderer()
        val rendered = renderer.render(player, player.displayName(), chatMessage, Audience.empty())
        plugin.addMessageToCache(rendered)

        if (plugin.isDebugMode()) {
            Bukkit.getLogger().info("[PriorChat] Caching CHAT message: $rendered")
        }
    }
}