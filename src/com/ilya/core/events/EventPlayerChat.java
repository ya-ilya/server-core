package com.ilya.core.events;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.ilya.core.Main;

public class EventPlayerChat implements Listener {
	
	public EventPlayerChat(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}
	
    public static Set<Player> players = new HashSet<>();
    
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		Player sender = event.getPlayer();
		for (Player p : Main.getInstance().getServer().getOnlinePlayers()) {
			if (Main.ignoreManager.getConfig().getStringList("players." + p.getName() + ".ignoring") == null) continue;
			List<String> ignoreList = Main.ignoreManager.getConfig().getStringList("players." + p.getName() + ".ignoring");
			for (String ignored : ignoreList) {
				if (!ignored.equalsIgnoreCase(sender.getName())) continue;
				event.getRecipients().remove(p);
			}
		}
		String message = event.getMessage();
		if (message.startsWith(">")) {
			event.setMessage(ChatColor.GREEN + message);
		}
		if (message.startsWith(":")) {
			event.setMessage(ChatColor.AQUA + message);
		}
		if (message.startsWith("!")) {
			event.setMessage(ChatColor.YELLOW + message);
		}
	}
}
