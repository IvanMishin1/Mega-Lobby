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
        Objects.requireNonNull(getCommand("queue")).setTabCompleter(new TabCompleter(gameManager));

        getLogger().info("Creating queues");
        this.saveDefaultConfig();
        for(String gameKey : this.getConfig().getConfigurationSection("games").getKeys(false)) {
            String gameName = this.getConfig().getString("games." + gameKey + ".game-name");
            String serverName = gameKey;
            int maxPlayers = this.getConfig().getInt("games." + gameKey + ".max-players");
            String guiItem = this.getConfig().getString("games." + gameKey + ".gui-item");
            getLogger().info("- Creating queue for " + gameName + " with server name " + serverName + " and max players " + maxPlayers + " and gui item " + guiItem);
            gameManager.createGame(gameName, serverName, maxPlayers, guiItem);
        }

        getLogger().info("Mega-Lobby has been enabled!");

    }
    public GameManager getgameManager() {
        return gameManager;
    }

    @Override
    public void onDisable() {
        gameManager.deleteAllGames();
        getLogger().info("All queues have been cleared!");
        getLogger().info("Mega-Lobby has been disabled!");
    }
}
