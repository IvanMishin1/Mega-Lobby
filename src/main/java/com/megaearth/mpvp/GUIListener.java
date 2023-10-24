package com.megaearth.mpvp;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.entity.Player;

public class GUIListener implements Listener {

    MPVP plugin = (MPVP) MPVP.getPlugin(MPVP.class);

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getHolder() instanceof Player) {
            Player player = (Player) event.getInventory().getHolder();

            if (player.hasMetadata("OpenedMenu")) {
                event.setCancelled(true);

                //get the clicked item and perform the action
                switch (event.getCurrentItem().getType()) {
                    case DIAMOND_SWORD:
                        player.sendMessage("You clicked on the diamond sword!");
                        break;
                    default:
                        break;
                }
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getPlayer() instanceof Player) {
            Player player = (Player) event.getPlayer();

            if (player.hasMetadata("OpenedMenu")) {
                player.removeMetadata("OpenedMenu", plugin);
            }
        }
    }
}
