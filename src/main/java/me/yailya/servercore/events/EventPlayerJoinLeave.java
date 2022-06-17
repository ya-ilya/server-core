package me.yailya.servercore.events;

import me.yailya.servercore.Main;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventPlayerJoinLeave implements Listener {
    public EventPlayerJoinLeave() {
        Main.registerListener(this);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(ChatColor.GRAY + Main.getInstance().getConfig().getString("joinMessage")
                .replaceAll("%player%", event.getPlayer().getName()));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage(ChatColor.GRAY + Main.getInstance().getConfig().getString("leftMessage")
                .replaceAll("%player%", event.getPlayer().getName()));
    }
}