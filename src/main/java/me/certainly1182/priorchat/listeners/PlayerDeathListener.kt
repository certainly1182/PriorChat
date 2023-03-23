package me.certainly1182.priorchat.listeners

import me.certainly1182.priorchat.PriorChat
import me.certainly1182.priorchat.util.SchedulerUtil
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent

class PlayerDeathListener(private val plugin: PriorChat) : Listener {
    @EventHandler(ignoreCancelled = true)
    fun onPlayerDeath(event: PlayerDeathEvent) {
        SchedulerUtil.runOnNextTick(plugin) {
            val deathMessage = event.deathMessage()
            if (deathMessage != null) {
                plugin.addMessageToCache(deathMessage)
            }

            if (plugin.isDebugMode()) {
                Bukkit.getLogger().info("[PriorChat] Caching DEATH message: $deathMessage")
            }
        }
    }
}