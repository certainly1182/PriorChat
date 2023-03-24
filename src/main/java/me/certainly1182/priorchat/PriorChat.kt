package me.certainly1182.priorchat

import com.google.common.collect.EvictingQueue
import me.certainly1182.priorchat.listeners.*
import net.kyori.adventure.text.Component
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

class PriorChat : JavaPlugin() {
    private var debugMode: Boolean = false
    private lateinit var messageCache: Queue<Component>
    private enum class MessageType {
        JOIN,
        CHAT,
        QUIT,
        DEATH,
        BROADCAST,
        ADVANCEMENT,
        KICK
    }
    private lateinit var enabledMessageTypes: Set<MessageType>
    override fun onEnable() {
        saveDefaultConfig()
        debugMode = config.getBoolean("debug_mode", false)
        val cacheSize = config.getInt("number_of_messages_to_store", 50)
        println("[PriorChat] Cache Size: $cacheSize")
        messageCache = EvictingQueue.create(cacheSize)

        // Read enabled message types from config
        val enabledMessageTypesConfig = config.getStringList("enabled_message_types")
        enabledMessageTypes = enabledMessageTypesConfig.mapNotNull {
            try {
                MessageType.valueOf(it.uppercase())
            } catch (ex: IllegalArgumentException) {
                null
            }
        }.toSet()

        // Map of listeners
        val listeners = mapOf(
            MessageType.JOIN to PlayerJoinListener(this),
            MessageType.CHAT to PlayerChatListener(this),
            MessageType.QUIT to PlayerQuitListener(this),
            MessageType.DEATH to PlayerDeathListener(this),
            MessageType.BROADCAST to ServerBroadcastListener(this),
            MessageType.ADVANCEMENT to PlayerAdvancementDoneListener(this),
            MessageType.KICK to PlayerKickListener(this)
        )
        // Register event listeners
        for (type in enabledMessageTypes) {
            listeners[type]?.let { listener ->
                server.pluginManager.registerEvents(listener, this)
            }
        }
        server.pluginManager.registerEvents(LoginMessageHandler(this), this)
    }

    fun isDebugMode(): Boolean {
        return debugMode
    }

    fun getMessageCache(): Queue<Component> {
        return messageCache
    }

    fun addMessageToCache(message: Component) {
        messageCache.add(message)
    }
}