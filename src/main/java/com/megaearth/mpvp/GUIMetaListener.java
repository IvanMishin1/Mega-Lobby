package com.megaearth.mpvp;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.entity.Player;
import org.bukkit.Material;
import java.util.Objects;

public class GUIMetaListener implements Listener {

    MPVP plugin = (MPVP) MPVP.getPlugin(MPVP.class);

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getHolder() instanceof Player) {
            Player player = (Player) event.getInventory().getHolder();
            if (player.hasMetadata("OpenedMenu")) {
                event.setCancelled(true);
                if (Objects.requireNonNull(event.getCurrentItem()).getType().equals(Material.DIAMOND_SWORD)) {
                    event.getWhoClicked().closeInventory();
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
