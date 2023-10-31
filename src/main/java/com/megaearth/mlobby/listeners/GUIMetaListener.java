package com.megaearth.mlobby.listeners;
import com.megaearth.mlobby.mlobby;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.entity.Player;

public class GUIMetaListener implements Listener {

    // This class is used to prevent players from moving items in the GUI. It is not responsible for listening to what items are clicked.
    // That is the job of the class that implements the GUI.
    mlobby plugin = (mlobby) mlobby.getPlugin(mlobby.class);

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getHolder() instanceof Player) {
            Player player = (Player) event.getInventory().getHolder();
            if (player.hasMetadata("OpenedMenu")) {
                event.setCancelled(true);
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
