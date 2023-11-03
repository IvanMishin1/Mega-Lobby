package com.megaearth.mlobby;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import java.util.ArrayList;
import java.util.*;

public class QueueManager implements CommandExecutor {
    private final Map<String, GameQueue> queues;

    /* -------------------------------------------------------------------------- */
    /*                        Queue Management Methods                            */
    /* -------------------------------------------------------------------------- */

    public QueueManager() {
        this.queues = new HashMap<>();
    }

    public void createQueue(String gameName, String serverName) {
        if (!queues.containsKey(gameName)) {
            queues.put(serverName, new GameQueue(gameName, serverName));
        }
    }
    public void deleteQueue(String gameName) {
        queues.remove(gameName);
    }

    public void deleteAllQueues() {
        queues.clear();
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

    /* -------------------------------------------------------------------------- */
    /*                             Queue Command Stuff                            */
    /* -------------------------------------------------------------------------- */

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (args.length == 0) {
            commandSender.sendMessage("Invalid command");
            return true;
        }
        String action = args[0];
        if (args.length > 1 && (action.equalsIgnoreCase("join") || action.equalsIgnoreCase("leave") || action.equalsIgnoreCase("list"))) {
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
                    return true;
                case "list":
                    List<String> playerNames = new ArrayList<>();
                    for (Player player : queue.getPlayers()) {
                        playerNames.add(player.getName());
                    }
                    String playersMessage = String.join(", ", playerNames);
                    commandSender.sendMessage("Players in queue for " + gameName + ": " + playersMessage);
                    return true;
                default:
                    commandSender.sendMessage("Invalid command");
                    return true;
            }
        }
        if (args.length == 1) {
            switch (action) {
                case "listqueues":
                    String queuesMessage = String.join(", ", queues.keySet());
                    commandSender.sendMessage("Queues: " + queuesMessage);
                    return true;
                case "somecommand":
                    commandSender.sendMessage("somecommand");
                    return true;
                default:
                    commandSender.sendMessage("Invalid command");
                    return true;
            }
        }
        return true;
    }
}

/* -------------------------------------------------------------------------- */
/*                               Queue Class Stuff                            */
/* -------------------------------------------------------------------------- */

class GameQueue {
    private final String gameName;
    private final String serverName;
    private final Queue<Player> queue;

    public GameQueue(String gameName, String serverName) {
        this.gameName = gameName;
        this.serverName = serverName;
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
    public String getServerName() {
        return serverName;
    }

}
