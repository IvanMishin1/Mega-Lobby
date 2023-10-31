package com.megaearth.mpvp;

import com.megaearth.mpvp.listeners.GUIMetaListener;
import com.megaearth.mpvp.listeners.PlayerLeaveListener;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;

public final class MPVP extends JavaPlugin {
    private QueueManager queueManager;

    @Override
    public void onEnable() {
        queueManager = new QueueManager();
        getCommand("queue").setExecutor(queueManager);
        getCommand("gui").setExecutor(new PlayGUI(queueManager, this));

        this.getServer().getPluginManager().registerEvents(new GUIMetaListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerLeaveListener(queueManager), this);

        getCommand("queue").setTabCompleter(new QueueTabCompleter(queueManager));


        queueManager.createQueue("Game1");
        queueManager.createQueue("Game2");

        getLogger().info("MPVP has been enabled!");
    }
    public QueueManager getQueueManager() {
        return queueManager;
    }

    @Override
    public void onDisable() {
        queueManager.deleteAllQueues();
        getLogger().info("All queues have been cleared!");
        getLogger().info("MPVP has been disabled!");
    }
}
