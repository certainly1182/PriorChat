package me.certainly1182.priorchat

import com.google.common.collect.EvictingQueue
import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.audience.Audience
import net.kyori.adventure.text.Component
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin
import java.util.*
import org.bukkit.Bukkit
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.scheduler.BukkitRunnable

class PriorChat : JavaPlugin(), Listener {
    private lateinit var messageCache: Queue<Component>

    override fun onEnable() {
        // Plugin startup logic
        saveDefaultConfig()
        val cacheSize = config.getInt("number_of_messages_to_store", 50)
        Bukkit.getLogger().info("Cache Size: $cacheSize")
        messageCache = EvictingQueue.create(cacheSize)

        // Register event listener
        server.pluginManager.registerEvents(this, this)
    }

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val player = event.player
        // send player history
        for (message in messageCache) {
            player.sendMessage(message)
        }
        runOnNextTick { messageCache.add(event.joinMessage()) }
    }

    @EventHandler
    fun onPlayerChat(event: AsyncChatEvent) {
        val player = event.player
        val message = event.message()
        val renderer = event.renderer()
        val rendered = renderer.render(player, player.displayName(), message, Audience.empty())
        messageCache.add(rendered)
    }

    @EventHandler
    fun onPlayerLeave(event: PlayerQuitEvent) {
        runOnNextTick { messageCache.add(event.quitMessage()) }
    }

    @EventHandler
    fun onPlayerDeath(event: PlayerDeathEvent) {
        runOnNextTick { messageCache.add(event.deathMessage()) }
    }

    // Schedule an expression to run next tick
    // allows other plugins to modify event message formats
    private fun runOnNextTick(block: () -> Unit) {
        object : BukkitRunnable() {
            override fun run() {
                block()
            }
        }.runTask(this)
    }
}