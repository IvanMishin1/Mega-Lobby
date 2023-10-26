package com.megaearth.mpvp.queue;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.entity.Player;
public class QueueManager {
    private final Map<String, GameQueue> queues;
    // THE METHODS IN GameQueue.java DO NOT PERFORM ANY ERROR CHECKING OR VALIDATION!
    // IF YOU ARE USING ANY OF THESE METHODS IN YOUR CODE, YOU MUST PERFORM ERROR CHECKING AND VALIDATION HERE!
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
            if(queue.containsPlayer(player))
            {
                queue.removePlayer(player);
            }
        }
    }

}
