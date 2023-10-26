package com.megaearth.mpvp.queue;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class QueueCommandExecutor implements CommandExecutor {
    private final QueueManager queueManager;

    public QueueCommandExecutor(QueueManager queueManager) {
        this.queueManager = queueManager;
    }
    // THE METHODS IN GameQueue.java DO NOT PERFORM ANY ERROR CHECKING OR VALIDATION!
    // IF YOU ARE USING ANY OF THESE METHODS IN YOUR CODE, YOU MUST PERFORM ERROR CHECKING AND VALIDATION HERE!
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (args.length > 1) {
                String action = args[0];
                String gameName = args[1];
                GameQueue queue = queueManager.getQueue(gameName);
                switch (action) {
                    case "join":
                        if (queue == null) {
                            player.sendMessage("That game does not exist");
                            break;
                        }
                        if (queue.containsPlayer(player)) {
                            player.sendMessage("You are already in the queue for " + gameName);
                            break;
                        }
                        else {
                            queue.addPlayer(player);
                            player.sendMessage("You have joined the queue for " + gameName);
                            break;
                        }
                    case "leave":
                        if (queue == null) {
                            player.sendMessage("That game does not exist");
                            break;
                        }
                        if (!queue.containsPlayer(player)) {
                            player.sendMessage("You are not in the queue for " + gameName);
                            break;
                        }
                        else {
                            queue.removePlayer(player);
                            player.sendMessage("You have left the queue for " + gameName);
                            break;
                        }
                    case "list":
                        if (queue == null) {
                            player.sendMessage("That game does not exist");
                            break;
                        }
                        else {
                            player.sendMessage("Players in queue for " + gameName + ": " + queue.getPlayers());
                            break;
                        }
                }
            }
        }

        return true;
    }
}
