package com.megaearth.mlobby;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import static org.bukkit.Bukkit.getLogger;

public class ChestGUI implements Listener {

    private String name;
    private int size;
    private OptionClickEventHandler handler;
    private Plugin plugin;

    private String[] optionNames;
    private ItemStack[] optionIcons;

    public ChestGUI(String name, int size, OptionClickEventHandler handler, Plugin plugin) {
        this.name = name;
        this.size = size;
        this.handler = handler;
        this.plugin = (mlobby) plugin;
        this.optionNames = new String[size];
        this.optionIcons = new ItemStack[size];
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public ChestGUI setOption(int position, ItemStack icon, String name, String... info) {
        if (position >= size) {
            if (position >= 54) {
                // Discard the option and log an error
                getLogger().warning("Error: Option position " + position + " is out of bounds of a double chest.");
                return this;
            }
            size = ((position / 9) + 1) * 9; // Increase the size of the menu to the next multiple of 9 greater than position
            optionNames = Arrays.copyOf(optionNames, size); // Resize the optionNames array
            optionIcons = Arrays.copyOf(optionIcons, size); // Resize the optionIcons array
        }
        optionNames[position] = name;
        optionIcons[position] = setItemNameAndLore(icon, name, info);
        return this;
    }

    public void open(Player player) {
        Inventory inventory = Bukkit.createInventory(player, size, name);
        for (int i = 0; i < optionIcons.length; i++) {
            if (optionIcons[i] != null) {
                inventory.setItem(i, optionIcons[i]);
            }
        }
        player.openInventory(inventory);
    }

    public void destroy() {
        HandlerList.unregisterAll(this);
        handler = null;
        plugin = null;
        optionNames = null;
        optionIcons = null;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();
            if (player.getOpenInventory().getTitle().equals(name)) {
                event.setCancelled(true);
                int slot = event.getRawSlot();
                if (slot >= 0 && slot < size && optionNames[slot] != null) {
                    Plugin plugin = this.plugin;
                    OptionClickEvent e = new OptionClickEvent((Player) event.getWhoClicked(), slot, optionNames[slot]);
                    handler.onOptionClick(e);
                    if (e.willClose()) {
                        final Player p = (Player) event.getWhoClicked();
                        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                            public void run() {
                                p.closeInventory();
                            }
                        }, 1);
                    }
                    if (e.willDestroy()) {
                        destroy();
                    }
                }
            }
        }
    }

    public interface OptionClickEventHandler {
        void onOptionClick(OptionClickEvent event);
    }

    public class OptionClickEvent {
        private Player player;
        private int position;
        private String name;
        private boolean close;
        private boolean destroy;

        public OptionClickEvent(Player player, int position, String name) {
            this.player = player;
            this.position = position;
            this.name = name;
            this.close = true;
            this.destroy = false;
        }

        public Player getPlayer() {
            return player;
        }

        public int getPosition() {
            return position;
        }

        public String getName() {
            return name;
        }

        public boolean willClose() {
            return close;
        }

        public boolean willDestroy() {
            return destroy;
        }

        public void setWillClose(boolean close) {
            this.close = close;
        }

        public void setWillDestroy(boolean destroy) {
            this.destroy = destroy;
        }
    }

    private ItemStack setItemNameAndLore(ItemStack item, String name, String[] lore) {
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(name);
        im.setLore(Arrays.asList(lore));
        item.setItemMeta(im);
        return item;
    }

}