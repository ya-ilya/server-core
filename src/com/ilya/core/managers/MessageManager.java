package com.ilya.core.managers;

import java.util.HashMap;

import org.bukkit.entity.Player;

import com.ilya.core.Main;

public class MessageManager {
	Main core;
	
	public MessageManager(Main core) {
		this.core = core;
	}
	HashMap<Player, Player> players = new HashMap<>();
	
	public void setReplyTarget(Player player1, Player player2) {
		players.put(player1, player2);
		players.put(player2, player1);
	}
	
	public Player getReplyTarget(Player player1) {
		return players.get(player1);
	}
}
