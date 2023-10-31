package com.megaearth.mpvp;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import com.megaearth.mpvp.IconMenu;

public class PlayGUI implements CommandExecutor {
    MPVP plugin = (MPVP) MPVP.getPlugin(MPVP.class);
    private final QueueManager queueManager;
    private final IconMenu menu;

    public PlayGUI(QueueManager queueManager, MPVP plugin) {
        this.queueManager = queueManager;
        this.menu = new IconMenu("Game Selection", 9, new IconMenu.OptionClickEventHandler() {
            @Override
            public void onOptionClick(IconMenu.OptionClickEvent event) {
                Player player = event.getPlayer();
                String clicked = event.getName();
                switch (clicked) {
                    case "Game1":
                        player.performCommand("queue join Game1");
                        player.closeInventory();
                        break;
                    case "Game2":
                        player.performCommand("queue join Game2");
                        player.closeInventory();
                        break;
                    case "Game3":
                        player.performCommand("queue join Game3");
                        player.closeInventory();
                        break;
                    case "Game4":
                        player.performCommand("queue join Game4");
                        player.closeInventory();
                        break;
                }
            }
        }, plugin);

        // Set menu options
        menu.setOption(0, new ItemStack(Material.DIAMOND_SWORD), "Game1");
        menu.setOption(1, new ItemStack(Material.TNT), "Game2");
        menu.setOption(2, new ItemStack(Material.GRASS_BLOCK), "Game3");
        menu.setOption(3, new ItemStack(Material.RED_BED), "Game4");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("gui")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("You must be a player to use this command!");
                return true;
            }
            Player player = (Player) sender;
            menu.open(player);
        }
        return true;
    }
}
