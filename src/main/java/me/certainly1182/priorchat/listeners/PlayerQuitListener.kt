package me.certainly1182.priorchat.listeners

import me.certainly1182.priorchat.PriorChat
import me.certainly1182.priorchat.util.SchedulerUtil
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent

class PlayerQuitListener(private val plugin: PriorChat) : Listener {
    @EventHandler(ignoreCancelled = true)
    fun onPlayerLeave(event: PlayerQuitEvent) {
        SchedulerUtil.runOnNextTick(plugin) {
            val quitMessage = event.quitMessage()
            if (quitMessage != null) {
                plugin.addMessageToCache(quitMessage)
            }

            if (plugin.isDebugMode()) {
                plugin.logger.info("[PriorChat] Caching QUIT message: $quitMessage")
            }
        }
    }
}