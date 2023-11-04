package com.megaearth.mlobby.listeners;

import com.megaearth.mlobby.GameManager;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveListener implements Listener {
    private final GameManager queueManager;
    public PlayerLeaveListener(GameManager queueManager) {
        this.queueManager = queueManager;
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        queueManager.removePlayerFromAllQueues(event.getPlayer());
    }
}
