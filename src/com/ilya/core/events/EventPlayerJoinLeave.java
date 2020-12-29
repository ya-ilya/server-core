package com.ilya.core.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.ilya.core.Main;

public class EventPlayerJoinLeave implements Listener {
	public EventPlayerJoinLeave(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		event.setJoinMessage(ChatColor.GRAY + p.getDisplayName() + " joined the game");
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        event.setQuitMessage(ChatColor.GRAY + p.getDisplayName() + " left the game");
    }
}
