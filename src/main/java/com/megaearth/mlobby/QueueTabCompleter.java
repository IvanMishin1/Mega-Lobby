package com.megaearth.mlobby;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class QueueTabCompleter implements TabCompleter {
    private final QueueManager queueManager;

    public QueueTabCompleter(QueueManager queueManager) {
        this.queueManager = queueManager;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String label, String[] args) {
        List<String> completions = new ArrayList<>();

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (args.length == 1) {
                completions.add("join");
                completions.add("leave");
                completions.add("list");
                completions.add("listqueues");
            }
            if (args.length == 2 && args[0].equalsIgnoreCase("join") || args[0].equalsIgnoreCase("leave") || args[0].equalsIgnoreCase("list")) {
                List<String> gameNames = new ArrayList<>();
                for (GameQueue gameQueue : queueManager.getAllQueues()) {
                    gameNames.add(gameQueue.getGameName());
                }
                return gameNames;
            }
        }

        return completions;
    }
}
