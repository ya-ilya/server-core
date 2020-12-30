package com.ilya.core.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ilya.core.Main;

@SuppressWarnings("deprecation")
public class JoinDate implements CommandExecutor {
	Main core;
	
	public JoinDate(Main core) {
		this.core = core;
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			if (args.length > 0) {
				if (core.getServer().getOfflinePlayer(args[0]) != null) {
					Player m = core.getServer().getOfflinePlayer(args[0]).getPlayer();
					try {
						String joindate = Main.playerManager.getConfig().getString("players." + m.getName() + ".joindate");
						sender.sendMessage(ChatColor.GOLD + m.getName() + "'s join date: " + ChatColor.GREEN + joindate);
					}catch (Exception e) {
						sender.sendMessage(ChatColor.RED + args[0] + " never joined in to the server");
						return true;
					}
				}else {
					sender.sendMessage(ChatColor.RED + args[0] + " never joined in to the server");
				}
				return true;
			}else {
				Player p = (Player) sender;
				if (Main.playerManager.getConfig().getString("players." + p.getName() + ".joindate") != null) {
					String joindate = Main.playerManager.getConfig().getString("players." + p.getName() + ".joindate");
					sender.sendMessage(ChatColor.GOLD + "Your join date: " + ChatColor.GREEN + joindate);
				}
				return true;
			}
		}
		return true;
	}

}
