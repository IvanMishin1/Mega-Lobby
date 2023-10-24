package com.megaearth.mpvp.commands;

import com.megaearth.mpvp.MPVP;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;

public class PlayGUI implements CommandExecutor{

    MPVP plugin = (MPVP) MPVP.getPlugin(MPVP.class);
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("play")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("You must be a player to use this command!");
                return true;
            }
            Player player = (Player) sender;
            Inventory gui = Bukkit.createInventory(player, 9 * 3, "Select a Game");
            gui.setItem(0, new ItemStack(Material.DIAMOND_CHESTPLATE));
            player.openInventory(gui);
            player.setMetadata("OpenedMenu", new FixedMetadataValue(plugin, true));
        }
        return true;
    }
}
