package com.megaearth.mlobby;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.*;

import static org.bukkit.Bukkit.getLogger;

public class GameManager implements CommandExecutor {
    private final Map<String, Game> queues;

    /* -------------------------------------------------------------------------- */
    /*                        Game Management Methods                            */
    /* -------------------------------------------------------------------------- */

    public GameManager() {
        this.queues = new HashMap<>();
    }

    public void createGame(String gameName, String serverName, int maxPlayers, String guiItem) {
        if (!queues.containsKey(gameName)) {
            queues.put(serverName, new Game(gameName, serverName, maxPlayers, guiItem));
        }
    }

    public void deleteQueue(String gameName) {
        queues.remove(gameName);
    }

    public void deleteAllGames() {
        queues.clear();
    }

    public Game getQueue(String gameName) {
        return queues.get(gameName);
    }

    public void removePlayerFromAllQueues(Player player) {
        for (Game queue : queues.values()) {
            queue.removePlayer(player);
        }
    }

    public Collection<Game> getAllGames() {
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
            Game queue = getQueue(gameName);

            if (queue == null) {
                commandSender.sendMessage("That game does not exist");
                return true;
            }
            // For commands with 2 arguments
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
                    if (queue.getPlayers().isEmpty()) {
                        commandSender.sendMessage("There are no players in the queue for " + gameName);
                        return true;
                    }
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
            // For commands with 1 argument
            switch (action) {
                case "listqueues":
                    if (queues.isEmpty()) {
                        commandSender.sendMessage("There are no queues");
                        return true;
                    }
                    String queuesMessage = String.join(", ", queues.keySet());
                    commandSender.sendMessage("Queues: " + queuesMessage);
                    return true;
                default:
                    commandSender.sendMessage("Invalid command");
                    return true;
            }
        }
        return true;
    }

    /* -------------------------------------------------------------------------- */
    /*                               Matchmaking stuff                            */
    /* -------------------------------------------------------------------------- */

    public void Matchmake() {
        getLogger().info("Matchmaking...");
        for (Game queue : queues.values()) {
            if (queue.getPlayers().size() >= queue.maxPlayers) {
                List<Player> players = queue.getPlayers();
                for (Player player : players) {
                    player.sendMessage("You have been matched with " + queue.getFirstPlayerName());
                }
                queue.getPlayers().clear();
            }
        }
    }

    /* -------------------------------------------------------------------------- */
    /*                               Game Class Stuff                            */
    /* -------------------------------------------------------------------------- */

    class Game {
        private final String gameName;
        private final String serverName;
        private final int maxPlayers;
        private final String guiItem;
        private final Queue<Player> queue;

        public Game(String gameName, String serverName, int maxPlayers, String guiItem) {
            this.gameName = gameName;
            this.serverName = serverName;
            this.maxPlayers = maxPlayers;
            this.guiItem = guiItem;
            this.queue = new LinkedList<>();
        }

        public void addPlayer(Player player) {
            queue.add(player);
            Matchmake();
        }

        public void removePlayer(Player player) {
            queue.remove(player);
        }

        public String getGuiItem() {
            return guiItem;
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

        public String getFirstPlayerName() {
            assert queue.peek() != null;
            return queue.peek().getName();
        }
    }
}
