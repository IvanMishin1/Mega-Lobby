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
        /*
        menu.setOption(4, new ItemStack(Material.DIAMOND_SWORD), "Game1");
        menu.setOption(5, new ItemStack(Material.TNT), "Game2");
        menu.setOption(6, new ItemStack(Material.GRASS_BLOCK), "Game3");
        menu.setOption(7, new ItemStack(Material.RED_BED), "Game4");

        menu.setOption(8, new ItemStack(Material.DIAMOND_SWORD), "Game1");
        menu.setOption(9, new ItemStack(Material.TNT), "Game2");
        menu.setOption(10, new ItemStack(Material.GRASS_BLOCK), "Game3");
        menu.setOption(11, new ItemStack(Material.RED_BED), "Game4");

        menu.setOption(11, new ItemStack(Material.DIAMOND_SWORD), "Game1");
        menu.setOption(12, new ItemStack(Material.TNT), "Game2");
        menu.setOption(13, new ItemStack(Material.GRASS_BLOCK), "Game3");
        menu.setOption(14, new ItemStack(Material.RED_BED), "Game4");

        menu.setOption(15, new ItemStack(Material.DIAMOND_SWORD), "Game1");
        menu.setOption(16, new ItemStack(Material.TNT), "Game2");
        menu.setOption(17, new ItemStack(Material.GRASS_BLOCK), "Game3");
        menu.setOption(18, new ItemStack(Material.RED_BED), "Game4");

        menu.setOption(19, new ItemStack(Material.DIAMOND_SWORD), "Game1");
        menu.setOption(20, new ItemStack(Material.TNT), "Game2");
        menu.setOption(21, new ItemStack(Material.GRASS_BLOCK), "Game3");
        menu.setOption(22, new ItemStack(Material.RED_BED), "Game4");

        menu.setOption(23, new ItemStack(Material.DIAMOND_SWORD), "Game1");
        menu.setOption(24, new ItemStack(Material.TNT), "Game2");
        menu.setOption(25, new ItemStack(Material.GRASS_BLOCK), "Game3");
        menu.setOption(26, new ItemStack(Material.RED_BED), "Game4");

        menu.setOption(27, new ItemStack(Material.DIAMOND_SWORD), "Game1");
        menu.setOption(28, new ItemStack(Material.TNT), "Game2");
        menu.setOption(29, new ItemStack(Material.GRASS_BLOCK), "Game3");
        menu.setOption(30, new ItemStack(Material.RED_BED), "Game4");

        menu.setOption(31, new ItemStack(Material.DIAMOND_SWORD), "Game1");
        menu.setOption(32, new ItemStack(Material.TNT), "Game2");
        menu.setOption(33, new ItemStack(Material.GRASS_BLOCK), "Game3");
        menu.setOption(34, new ItemStack(Material.RED_BED), "Game4");

        menu.setOption(35, new ItemStack(Material.DIAMOND_SWORD), "Game1");
        menu.setOption(36, new ItemStack(Material.TNT), "Game2");
        menu.setOption(37, new ItemStack(Material.GRASS_BLOCK), "Game3");
        menu.setOption(38, new ItemStack(Material.RED_BED), "Game4");

        menu.setOption(39, new ItemStack(Material.DIAMOND_SWORD), "Game1");
        menu.setOption(40, new ItemStack(Material.TNT), "Game2");
        menu.setOption(41, new ItemStack(Material.GRASS_BLOCK), "Game3");
        menu.setOption(42, new ItemStack(Material.RED_BED), "Game4");

        menu.setOption(43, new ItemStack(Material.DIAMOND_SWORD), "Game1");
        menu.setOption(44, new ItemStack(Material.TNT), "Game2");
        menu.setOption(45, new ItemStack(Material.GRASS_BLOCK), "Game3");
        menu.setOption(46, new ItemStack(Material.RED_BED), "Game4");

        menu.setOption(47, new ItemStack(Material.DIAMOND_SWORD), "Game1");
        menu.setOption(48, new ItemStack(Material.TNT), "Game2");
        menu.setOption(49, new ItemStack(Material.GRASS_BLOCK), "Game3");
        menu.setOption(50, new ItemStack(Material.RED_BED), "Game4");

        menu.setOption(51, new ItemStack(Material.DIAMOND_SWORD), "Game1");
        menu.setOption(52, new ItemStack(Material.TNT), "Game2");
        menu.setOption(53, new ItemStack(Material.GRASS_BLOCK), "Game3");
        menu.setOption(54, new ItemStack(Material.RED_BED), "Game4");
         */
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
