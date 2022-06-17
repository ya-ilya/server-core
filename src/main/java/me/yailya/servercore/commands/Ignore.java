package me.yailya.servercore.commands;

import me.yailya.servercore.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Ignore implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");

            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Please specify a player!");
            return true;
        }

        Player toIgnore = Main.getInstance().getServer().getOfflinePlayer(args[0]).getPlayer();
        List<String> ignoreList = Main.ignoreManager.getIgnoreList(sender.getName());

        if (toIgnore != null || ignoreList.contains(args[0])) {
            String toIgnoreName;

            if (toIgnore != null) {
                toIgnoreName = toIgnore.getName();
            } else {
                toIgnoreName = args[0];
            }

            if (toIgnore == sender && !ignoreList.contains(sender.getName())) {
                sender.sendMessage(ChatColor.RED + "You can't ignore yourself!");

                return true;
            }

            if (ignoreList.isEmpty()) {
                ignoreList.add(toIgnore.getName());
            } else {
                if (ignoreList.contains(toIgnoreName)) {
                    ignoreList.remove(toIgnoreName);
                    Main.ignoreManager.getConfig().set("players." + sender.getName() + ".ignoring", ignoreList);
                    Main.ignoreManager.saveConfig();
                    sender.sendMessage(ChatColor.GREEN + "You are no longer ignoring " + toIgnoreName);

                    return true;
                } else {
                    ignoreList.add(toIgnoreName);
                }
            }

            Main.ignoreManager.getConfig().set("players." + sender.getName() + ".ignoring", ignoreList);
            Main.ignoreManager.saveConfig();

            sender.sendMessage(ChatColor.GREEN + "You are now ignoring " + toIgnoreName);

            return true;
        } else {
            sender.sendMessage(ChatColor.RED + "Couldn't find player " + args[0]);
        }

        return true;
    }
}
