package me.certainly1182.priorchat

import com.google.common.collect.EvictingQueue
import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin
import java.util.*
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Bukkit
import org.bukkit.event.player.PlayerQuitEvent

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
        player.sendMessage("Chat History:")
        // send player history
        for (message in messageCache) {
            player.sendMessage(message)
        }
        // log the event
        messageCache.add(Component.text("[").color(NamedTextColor.GRAY)
            .append(Component.text("+").color(TextColor.fromHexString("#55FFC6")))
            .append(Component.text("] ").color(NamedTextColor.GRAY))
            .append(player.displayName()))
    }

    @EventHandler
    fun onPlayerChat(event: AsyncChatEvent) {
        val player = event.player
        val message = event.message()
        messageCache.add(Component.text()
            .append(player.displayName())
            .append(Component.text(": ").color(NamedTextColor.GRAY))
            .append(message).build())
    }

    @EventHandler
    fun onPlayerLeave(event: PlayerQuitEvent) {
        val player = event.player
        // log the event
        messageCache.add(Component.text("[").color(NamedTextColor.GRAY)
            .append(Component.text("-").color(TextColor.fromHexString("#C655FF")))
            .append(Component.text("] ").color(NamedTextColor.GRAY))
            .append(player.displayName()))
    }
}