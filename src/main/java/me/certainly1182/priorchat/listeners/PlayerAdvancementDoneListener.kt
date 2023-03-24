package me.certainly1182.priorchat.listeners

import me.certainly1182.priorchat.PriorChat
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.GameRule
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerAdvancementDoneEvent

class PlayerAdvancementDoneListener(private val plugin: PriorChat) : Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    fun onPlayerAdvancement(event: PlayerAdvancementDoneEvent) {
        val advancementMessage = event.message() ?: return
        if (event.player.world.getGameRuleValue(GameRule.ANNOUNCE_ADVANCEMENTS) == false) return
        plugin.addMessageToCache(advancementMessage)

        if (plugin.isDebugMode()) {
            val log = PlainTextComponentSerializer.plainText().serialize(advancementMessage)
            println("[PriorChat] Caching ADVANCEMENT message: $log")
        }
    }
}