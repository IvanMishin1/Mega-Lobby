package com.megaearth.mpvp.listeners;

import com.megaearth.mpvp.QueueManager;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveListener implements Listener {
    private final QueueManager queueManager;
    public PlayerLeaveListener(QueueManager queueManager) {
        this.queueManager = queueManager;
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        queueManager.removePlayerFromAllQueues(event.getPlayer());
    }
}
