package com.megaearth.mlobby;

import com.megaearth.mlobby.listeners.GUIMetaListener;
import com.megaearth.mlobby.listeners.PlayerLeaveListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class mlobby extends JavaPlugin {
    private QueueManager queueManager;

    @Override
    public void onEnable() {
        queueManager = new QueueManager();

        getLogger().info("Registering commands");
        getCommand("queue").setExecutor(queueManager);
        getCommand("gui").setExecutor(new PlayGUI(queueManager, this));

        getLogger().info("Registering listeners");
        this.getServer().getPluginManager().registerEvents(new GUIMetaListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerLeaveListener(queueManager), this);

        getLogger().info("Enabling tab completion");
        getCommand("queue").setTabCompleter(new QueueTabCompleter(queueManager));

        getLogger().info("Creating queues");
        queueManager.createQueue("Mega-Earth","earth");
        queueManager.createQueue("Mega-PVP","pvp");

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
