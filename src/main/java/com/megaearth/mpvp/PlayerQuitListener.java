package com.megaearth.mpvp;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;
import com.megaearth.mpvp.queue.*;
import org.bukkit.entity.Player;

/* This class is responsible for handling what happens to players when they quit the game */

public class PlayerQuitListener implements Listener {
    private final QueueManager queueManager;
    public PlayerQuitListener(QueueManager queueManager) {
        this.queueManager = queueManager;
    }
}
