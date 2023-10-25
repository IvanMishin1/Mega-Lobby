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
        this.getCommand("play").setExecutor(new PlayGUI());
        this.getCommand("settings").setExecutor(new SettingsGUI());
        this.getServer().getPluginManager().registerEvents(new GUIMetaListener(), this);
        getLogger().info("MPVP has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("MPVP has been disabled!");
    }
}
