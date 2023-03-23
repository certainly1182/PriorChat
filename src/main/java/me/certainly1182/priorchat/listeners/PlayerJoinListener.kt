package me.certainly1182.priorchat.listeners

import me.certainly1182.priorchat.PriorChat
import me.certainly1182.priorchat.util.SchedulerUtil
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerJoinListener(private val plugin: PriorChat) : Listener {
    @EventHandler(ignoreCancelled = true)
    fun onPlayerJoin(event: PlayerJoinEvent) {
        SchedulerUtil.runOnNextTick(plugin) {
            val joinMessage = event.joinMessage()
            if (joinMessage != null) {
                plugin.addMessageToCache(joinMessage)
            }

            if (plugin.isDebugMode()) {
                plugin.logger.info("[PriorChat] Caching JOIN message: $joinMessage")
            }
        }
    }
}