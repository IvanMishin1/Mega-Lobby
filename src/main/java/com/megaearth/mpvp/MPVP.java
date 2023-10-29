package com.megaearth.mpvp;

import org.bukkit.plugin.java.JavaPlugin;

public final class MPVP extends JavaPlugin {
    private QueueManager queueManager;

    @Override
    public void onEnable() {
        queueManager = new QueueManager();
        getCommand("queue").setExecutor(queueManager);
        getCommand("play").setExecutor(new PlayGUI(queueManager));

        this.getServer().getPluginManager().registerEvents(new GUIMetaListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayGUI(queueManager), this);

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
        getLogger().info("MPVP has been disabled!");
    }
}
