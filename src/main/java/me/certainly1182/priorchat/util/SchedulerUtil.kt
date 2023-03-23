package me.certainly1182.priorchat.util

import me.certainly1182.priorchat.PriorChat
import org.bukkit.scheduler.BukkitRunnable

class SchedulerUtil {
    companion object {
        fun runOnNextTick(plugin: PriorChat, block: () -> Unit) {
            object : BukkitRunnable() {
                override fun run() {
                    block()
                }
            }.runTask(plugin)
        }
    }
}