package me.certainly1182.priorchat

import com.google.common.collect.EvictingQueue
import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin
import java.util.*
import com.Zrips.CMI.CMI
import io.papermc.paper.event.player.AbstractChatEvent
import org.bukkit.Bukkit

class PriorChat : JavaPlugin(), Listener {
    companion object {
        enum class Type {
            Chat, Join, Leave
        }
        data class Message(val type: Type, val player: Player, val message: Component)
        lateinit var MESSAGE_CACHE: Queue<Message>
    }

    override fun onEnable() {
        // Plugin startup logic
        saveDefaultConfig()
        val cacheSize = config.getInt("number_of_messages_to_store", 50)
        Bukkit.getLogger().info("Cache Size: $cacheSize")
        MESSAGE_CACHE = EvictingQueue.create(cacheSize)

        // Register event listener
        server.pluginManager.registerEvents(this, this)
    }

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val player = event.player
        player.sendMessage("Chat History:")
        // send player history
        for (message in MESSAGE_CACHE) {
            val formattedMessage = formatMessage(message.player, message.message)
            println(formattedMessage)
            player.sendMessage(formattedMessage)
        }
        // log the event
        val message = Component.text(" joined the game").color(NamedTextColor.YELLOW)
        MESSAGE_CACHE.add(Message(Type.Join, player, message))
    }

    @EventHandler
    fun onPlayerChat(event: AsyncChatEvent) {
        val player = event.player
        val message = event.message()
        MESSAGE_CACHE.add(Message(Type.Chat, player, message))
        println(Message(Type.Chat, player, message))
    }

    /*@EventHandler
    fun onPlayerLeave(event: PlayerQuitEvent) {
        val player = event.player
        val message = Component.text(" left the game").color(NamedTextColor.YELLOW)
        MESSAGE_CACHE.add(Message(Type.Leave, player, message))
    }*/

    private fun formatMessage(player: Player, message: Component): Component {
        val prefix: Component
        val name: Component
        /*if (checkInstalled("CMI")) {
            val cmiUser = CMI.getInstance().playerManager.getUser(player)
            prefix = Component.text(cmiUser.prefix)
            name = Component.text(cmiUser.displayName)
        } else {
            prefix = Component.text("")
            name = player.displayName()
        }*/
        name = player.displayName()
        prefix = Component.text("")
        return prefix.append(name)
            .append(Component.text(": ").color(NamedTextColor.GRAY))
            .append(message)

    }

    private fun checkInstalled(plugin: String): Boolean{
        return server.pluginManager.getPlugin(plugin) != null
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}