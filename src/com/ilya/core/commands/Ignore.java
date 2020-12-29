package com.ilya.core.commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ilya.core.Main;

public class Ignore implements CommandExecutor {
	Main core;
	
	public Ignore(Main core) {
		this.core = core;
	}
	
	public static List<String> ignoring = Main.ignoringManager.getIgnoring();
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can ignore other players!");
            return true;
		}
		if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Please specify a player!");
            return true;
        }
		Player player1 = (Player) sender;
		if (core.getServer().getOfflinePlayer(args[0]).getPlayer() != null) {
			Player player2 = core.getServer().getOfflinePlayer(args[0]).getPlayer();
			if (Main.ignoringManager.get("players." + player1.getName() + ".ignoring") == null) {
				Main.ignoringManager.clear();
				Main.ignoringManager.add(player2);
				Main.ignoringManager.set("players." + player1.getName() + ".ignoring");
				Main.ignoringManager.saveConfig();
				sender.sendMessage(ChatColor.GREEN + "You are now ignoring " + player2.getName());
				return true;
			}else {
				ignoring = Main.ignoringManager.getConfig().getStringList("players." + player1.getName() + ".ignoring");
				if (ignoring.contains(player2.getName())) {
					ignoring.remove(player2.getName());
					Main.ignoringManager.set("players." + player1.getName() + ".ignoring", ignoring);
					Main.ignoringManager.saveConfig();
	                sender.sendMessage(ChatColor.RED + "You are no longer ignoring " + player2.getName());
	                return true;
	            }
				ignoring.add(player2.getName());
				Main.ignoringManager.set("players." + player1.getName() + ".ignoring", ignoring);
				Main.ignoringManager.saveConfig();
	            sender.sendMessage(ChatColor.GREEN + "You are now ignoring " + player2.getName());
	            return true;
			}
		}else {
			sender.sendMessage(ChatColor.RED + "Could not find player " + args[0]);
		}
		return true;
	}
}
