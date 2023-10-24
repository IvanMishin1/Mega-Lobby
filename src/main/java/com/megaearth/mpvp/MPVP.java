package com.megaearth.mpvp;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class MPVP extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Objects.requireNonNull(this.getCommand("settings")).setExecutor((CommandExecutor) new SettingsCommand());
        Objects.requireNonNull(this.getCommand("play")).setExecutor((CommandExecutor) new PlayCommand());
        this.getServer().getPluginManager().registerEvents(new GUIListener(), this);
        getLogger().info("MPVP has been enabled!");

    }

    @Override
    public void onDisable() {
        getLogger().info("MPVP has been disabled!");
    }
}
