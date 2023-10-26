package com.megaearth.mpvp.queue;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GameQueue {
    private final String gameName;
    private final Queue<Player> queue;

    public GameQueue(String gameName) {
        this.gameName = gameName;
        this.queue = new LinkedList<>();
    }

    // THE METHODS BELOW DO NOT PERFORM ANY ERROR CHECKING OR VALIDATION!
    // ERROR CHECKING AND VALIDATION IS PERFORMED IN QueueCommandExecutor.java!
    public void addPlayer(Player player) {
        queue.add(player);
    }

    public void removePlayer(Player player) {
        queue.remove(player);
    }

    public boolean containsPlayer(Player player) {
        return queue.contains(player);
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(queue);
    }

    public String getGameName() {
        return gameName;
    }
}
