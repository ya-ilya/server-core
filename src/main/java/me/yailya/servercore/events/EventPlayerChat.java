package me.yailya.servercore.events;

import me.yailya.servercore.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;

public class EventPlayerChat implements Listener {

    public EventPlayerChat() {
        Main.registerListener(this);
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player sender = event.getPlayer();

        for (Player player : Main.getInstance().getServer().getOnlinePlayers()) {
            List<String> ignoreList = Main.ignoreManager.getIgnoreList(player.getName());

            for (String ignored : ignoreList) {
                if (!ignored.equalsIgnoreCase(sender.getName())) continue;

                event.getRecipients().remove(player);
            }
        }

        String message = event.getMessage();

        if (message.startsWith(">")) {
            event.setMessage(ChatColor.GREEN + message);
        } else if (message.startsWith(":")) {
            event.setMessage(ChatColor.AQUA + message);
        } else if (message.startsWith("!")) {
            event.setMessage(ChatColor.YELLOW + message);
        }
    }
}