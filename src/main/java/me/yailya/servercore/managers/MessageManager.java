package me.yailya.servercore.managers;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class MessageManager {
    private final HashMap<Player, Player> players = new HashMap<>();

    public void setReplyTarget(Player from, Player to) {
        players.put(from, to);
        players.put(to, from);
    }

    public Player getReplyTarget(Player player) {
        return players.get(player);
    }
}