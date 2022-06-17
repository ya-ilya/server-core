package me.yailya.servercore.commands;

import me.yailya.servercore.Main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class IgnoreList implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(org.bukkit.ChatColor.RED + "Only players can use this command!");

            return true;
        }

        List<String> ignoreList = Main.ignoreManager.getIgnoreList(sender.getName());

        if (ignoreList.size() > 0) {
            sender.sendMessage(ChatColor.GREEN + "Ignore list: " + String.join(", ", ignoreList));

            return true;
        }

        sender.sendMessage(ChatColor.RED + "0 players ignored");

        return true;
    }
}
