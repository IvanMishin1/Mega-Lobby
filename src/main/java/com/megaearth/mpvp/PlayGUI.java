package com.megaearth.mpvp;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.entity.Player.*;

public class PlayGUI implements CommandExecutor, Listener {
    private final QueueManager queueManager;

    public PlayGUI(QueueManager queueManager) {
        this.queueManager = queueManager;
    }
    MPVP plugin = (MPVP) MPVP.getPlugin(MPVP.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("play")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("You must be a player to use this command!");
                return true;
            }
            Player player = (Player) sender;
            Inventory gui = Bukkit.createInventory(player, 9 * 3, "Select a Game");
            gui.setItem(10, new ItemStack(Material.DIAMOND_SWORD));
            gui.setItem(12, new ItemStack(Material.TNT));
            gui.setItem(14, new ItemStack(Material.GRASS_BLOCK));
            gui.setItem(16, new ItemStack(Material.RED_BED));
            player.openInventory(gui);
            player.setMetadata("OpenedMenu", new FixedMetadataValue(plugin, true));
        }
        return true;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getHolder() instanceof Player) {
            Player player = (Player) event.getInventory().getHolder();
            String clicked = event.getCurrentItem().getType().toString();
            switch (clicked) {
                case "DIAMOND_SWORD":
                    player.performCommand("queue join Game1");
                    player.closeInventory();
                    break;
                case "TNT":
                    player.performCommand("queue join Game2");
                    player.closeInventory();
                    break;
                case "GRASS_BLOCK":
                    player.performCommand("queue join Game3");
                    player.closeInventory();
                    break;
                case "RED_BED":
                    player.performCommand("queue join Game4");
                    player.closeInventory();
                    break;
            }
        }
    }
}
