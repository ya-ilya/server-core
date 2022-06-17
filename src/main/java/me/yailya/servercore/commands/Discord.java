package me.yailya.servercore.commands;

import me.yailya.servercore.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Discord implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        sender.sendMessage(ChatColor.GOLD + "Join in the discord: " + ChatColor.GREEN + Main.getInstance().getConfig().getString("discord-invite"));

        return true;
    }
}
