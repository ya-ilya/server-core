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
					Player p = core.getServer().getOfflinePlayer(args[0]).getPlayer();
					try {
						String joinDate = Main.playerManager.getJoinDate(p);
						sender.sendMessage(ChatColor.GOLD + p.getName() + "'s join date: " + ChatColor.GREEN + joinDate);
					}catch (Exception e) {
						sender.sendMessage(ChatColor.RED + args[0] + " never joined in to the server");
						return true;
					}
				}else {
					sender.sendMessage(ChatColor.RED + args[0] + " never joined in to the server");
				}
			}else {
				Player p = (Player) sender;
				if (Main.playerManager.getConfig().getString("players." + p.getName() + ".joinDate") != null) {
					String joinDate = Main.playerManager.getJoinDate(p);
					sender.sendMessage(ChatColor.GOLD + "Your join date: " + ChatColor.GREEN + joinDate);
				}
			}
			return true;
		}
		return true;
	}

}
