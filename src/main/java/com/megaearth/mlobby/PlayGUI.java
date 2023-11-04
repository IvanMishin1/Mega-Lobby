package com.megaearth.mlobby;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class PlayGUI implements CommandExecutor {
    mlobby plugin = (mlobby) mlobby.getPlugin(mlobby.class);
    private final GameManager gameManager;
    private final ChestGUI menu;

    public PlayGUI(GameManager queueManager, mlobby plugin) {
        this.gameManager = queueManager;
        this.menu = new ChestGUI("Game Selection", 9, new ChestGUI.OptionClickEventHandler() {
            @Override
            public void onOptionClick(ChestGUI.OptionClickEvent event) {
                Player player = event.getPlayer();
                String clicked = event.getName();
                switch (clicked) {
                    case "Mega-Earth":
                        player.performCommand("queue join earth");
                        player.closeInventory();
                        break;
                    case "Mega-PVP":
                        player.performCommand("queue join pvp");
                        player.closeInventory();
                        break;
                }
            }
        }, plugin);

        // Set menu options
        menu.setOption(0, new ItemStack(Material.GRASS_BLOCK), "Mega-Earth");
        menu.setOption(1, new ItemStack(Material.DIAMOND_SWORD), "Mega-PVP");

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
