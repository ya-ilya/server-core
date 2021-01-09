package com.ilya.core.events;

import java.sql.Date;
import java.text.SimpleDateFormat;

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
		String joinMessage = Main.getInstance().getConfig().getString("joinMessage").replaceAll("%player%", p.getName());
		event.setJoinMessage(ChatColor.GRAY + joinMessage);
		if (Main.playerManager.getConfig().getString("players." + p.getName() + ".joinDate") != null) {

		}else {
			SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
			Date date = new Date(System.currentTimeMillis());
			Main.playerManager.getConfig().set("players." + p.getName() + ".joinDate", formatter.format(date));
			Main.playerManager.saveConfig();
		}
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        String leftMessage = Main.getInstance().getConfig().getString("leftMessage").replaceAll("%player%", p.getName());
        event.setQuitMessage(ChatColor.GRAY + leftMessage);
    }
}
