package com.megaearth.mpvp;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.*;

public class QueueManager implements CommandExecutor {
    private final Map<String, GameQueue> queues;

    public QueueManager() {
        this.queues = new HashMap<>();
    }

    public void createQueue(String gameName) {
        if (!queues.containsKey(gameName)) {
            queues.put(gameName, new GameQueue(gameName));
        }
    }

    public GameQueue getQueue(String gameName) {
        return queues.get(gameName);
    }

    public void removePlayerFromAllQueues(Player player) {
        for (GameQueue queue : queues.values()) {
            queue.removePlayer(player);
        }
    }

    public Collection<GameQueue> getAllQueues() {
        return queues.values();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        String action = args[0];

        if (args.length > 1) {
            String gameName = args[1];
            GameQueue queue = getQueue(gameName);

            if (queue == null) {
                commandSender.sendMessage("That game does not exist");
                return true;
            }

            switch (action) {
                case "join":
                    if (commandSender instanceof Player) {
                        Player player = (Player) commandSender;
                        if (queue.getPlayers().contains(player)) {
                            player.sendMessage("You are already in the queue for " + gameName);
                            return true;
                        }
                        queue.addPlayer(player);
                        player.sendMessage("You have joined the queue for " + gameName);
                    } else {
                        commandSender.sendMessage("Only players can join queues");
                    }
                    break;
                case "leave":
                    if (commandSender instanceof Player) {
                        Player player = (Player) commandSender;
                        if (!queue.getPlayers().contains(player)) {
                            player.sendMessage("You are not in the queue for " + gameName);
                            return true;
                        }
                        queue.removePlayer(player);
                        player.sendMessage("You have left the queue for " + gameName);
                    } else {
                        commandSender.sendMessage("Only players can leave queues");
                    }
                    break;
                case "list":
                    commandSender.sendMessage("Players in queue for " + gameName + ": " + queue.getPlayers());
                    break;
                default:
                    commandSender.sendMessage("Invalid action");
                    break;
            }
        } else if (args.length == 1) {
            switch (action) {
                case "listqueues":
                    String queuesMessage = String.join(", ", queues.keySet());
                    commandSender.sendMessage("Queues: " + queuesMessage);
                    break;
                default:
                    commandSender.sendMessage("Invalid command");
                    break;
            }
        }

        return true;
    }

}

class GameQueue {
    private final String gameName;
    private final Queue<Player> queue;

    public GameQueue(String gameName) {
        this.gameName = gameName;
        this.queue = new LinkedList<>();
    }

    public void addPlayer(Player player) {
        queue.add(player);
    }

    public void removePlayer(Player player) {
        queue.remove(player);
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(queue);
    }

    public String getGameName() {
        return gameName;
    }
}