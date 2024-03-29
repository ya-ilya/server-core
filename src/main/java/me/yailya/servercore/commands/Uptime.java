package me.yailya.servercore.commands;

import me.yailya.servercore.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Uptime implements CommandExecutor {
    public static String getUptime(long ms) {
        long seconds = ms / 1000L % 60L;
        long minutes = ms / 60000L % 60L;
        long hours = ms / 3600000L % 24L;
        long days = ms / 86400000L;

        return String.format("%dd %02dh %02dm %02ds", days, hours, minutes, seconds);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        sender.sendMessage(ChatColor.GOLD + "Uptime: " + ChatColor.GREEN + getUptime(System.currentTimeMillis() - Main.startTime));

        return true;
    }
}
