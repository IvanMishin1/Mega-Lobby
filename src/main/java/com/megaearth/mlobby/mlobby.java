package com.megaearth.mlobby;

import com.megaearth.mlobby.listeners.GUIMetaListener;
import com.megaearth.mlobby.listeners.PlayerLeaveListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class mlobby extends JavaPlugin {
    private GameManager gameManager;

    @Override
    public void onEnable() {
        gameManager = new GameManager();

        getLogger().info("Registering commands");
        Objects.requireNonNull(getCommand("queue")).setExecutor(gameManager);
        Objects.requireNonNull(getCommand("gui")).setExecutor(new PlayGUI(gameManager, this));

        getLogger().info("Registering listeners");
        this.getServer().getPluginManager().registerEvents(new GUIMetaListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerLeaveListener(gameManager), this);

        getLogger().info("Enabling tab completion");
        Objects.requireNonNull(getCommand("queue")).setTabCompleter(new QueueTabCompleter(gameManager));

        getLogger().info("Creating queues");
        gameManager.createQueue("Mega-Earth","earth");
        gameManager.createQueue("Mega-PVP","pvp");

        getLogger().info("MPVP has been enabled!");
    }
    public GameManager getgameManager() {
        return gameManager;
    }

    @Override
    public void onDisable() {
        gameManager.deleteAllGames();
        getLogger().info("All queues have been cleared!");
        getLogger().info("MPVP has been disabled!");
    }
}
