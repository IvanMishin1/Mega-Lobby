package com.megaearth.mpvp;
import org.bukkit.entity.Player;
import java.util.*;

public class Matchmaking {
    List<Player> queue = new ArrayList<>();

    public void addPlayerToQueue(Player player) {
        if (checkIfInQueue(player)) {
            player.sendMessage("You are already in the queue!");
            return;
        }
        queue.add(player);
        player.sendMessage("Waiting for a player!");
    }

    public void removePlayerFromQueue(Player player) {
        queue.remove(player);
    }
    public boolean checkIfInQueue(Player player) {
        return queue.contains(player);
    }

    public void matchPlayers() {
        if (queue.size() >= 2) {
            Player player1 = queue.get(0);
            Player player2 = queue.get(1);
            queue.remove(player1);
            queue.remove(player2);

            player1.kickPlayer("You have been matched with " + player2.getName());
            player2.kickPlayer("You have been matched with " + player1.getName());
        }
    }
}
