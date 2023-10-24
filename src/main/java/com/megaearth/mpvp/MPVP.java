package com.megaearth.mpvp;

import com.megaearth.mpvp.commands.PlayGUI;
import com.megaearth.mpvp.commands.SettingsGUI;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class MPVP extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Objects.requireNonNull(this.getCommand("settings")).setExecutor((CommandExecutor) new SettingsGUI());
        Objects.requireNonNull(this.getCommand("play")).setExecutor((CommandExecutor) new PlayGUI());
        this.getServer().getPluginManager().registerEvents(new GUIListener(), this);
        getLogger().info("MPVP has been enabled!");

    }

    @Override
    public void onDisable() {
        getLogger().info("MPVP has been disabled!");
    }
}
