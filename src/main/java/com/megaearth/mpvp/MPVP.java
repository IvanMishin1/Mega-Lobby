package com.megaearth.mpvp;
import com.megaearth.mpvp.commands.PlayGUI;
import org.bukkit.plugin.java.JavaPlugin;
import com.megaearth.mpvp.queue.*;

public final class MPVP extends JavaPlugin {

    private QueueManager queueManager;

    @Override
    public void onEnable() {
        queueManager = new QueueManager();
        // Plugin startup logic
        this.getCommand("play").setExecutor(new PlayGUI());
        getCommand("queue").setExecutor(new QueueCommandExecutor(queueManager));

        this.getServer().getPluginManager().registerEvents(new GUIMetaListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerQuitListener(queueManager), this);

        queueManager.createQueue("Game1");
        queueManager.createQueue("Game2");

        getLogger().info("MPVP has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("MPVP has been disabled!");
    }
}
