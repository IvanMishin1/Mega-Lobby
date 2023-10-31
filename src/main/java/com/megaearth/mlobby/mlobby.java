package com.megaearth.mlobby;

import com.megaearth.mlobby.listeners.GUIMetaListener;
import com.megaearth.mlobby.listeners.PlayerLeaveListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class mlobby extends JavaPlugin {
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
