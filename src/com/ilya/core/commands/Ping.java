package com.ilya.core.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ilya.core.Main;
import com.ilya.core.util.ContainerUtil;

public class Ping implements CommandExecutor {
	ContainerUtil util = new ContainerUtil();
	Main core;
	
	public Ping(Main core) {
		this.core = core;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
		}
		if (args.length > 0) {
			if (core.getServer().getOfflinePlayer(args[0]) != null) {
				if (core.getServer().getOfflinePlayer(args[0]).isOnline()) {
					Player player = core.getServer().getOfflinePlayer(args[0]).getPlayer();
					int ping = getPing(player);
					sender.sendMessage(ChatColor.GOLD + player.getName() + "`s ping is " + ChatColor.GREEN + ping);
					return true;
				}else {
					sender.sendMessage(ChatColor.RED + args[0] + " not online.");
					return true;
				}
			}
			return true;
		}else {
			Player player = (Player) sender;
			int ping = getPing(player);
			sender.sendMessage(ChatColor.GOLD + "Your ping is " + ChatColor.GREEN + ping);
			return true;
		}
	}
	
	public int getPing(Player player) {
		try {
			Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
			int ping = (int) entityPlayer.getClass().getField("ping").get(entityPlayer);
			return ping;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
