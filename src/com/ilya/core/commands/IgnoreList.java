package com.ilya.core.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ilya.core.Main;

import net.md_5.bungee.api.ChatColor;

public class IgnoreList implements CommandExecutor {
	Main core;
	
	public IgnoreList(Main core) {
		this.core = core;
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			List<String> ignoreList = Main.ignoreManager.getConfig().getStringList("players." + sender.getName() + ".ignoring");
			if (ignoreList.toString().length() > 0) {
				StringBuilder message = new StringBuilder();
				for (String s : ignoreList) {
					message.append(s).append(",");
				}
				
				if (message.length() > 0) {
					sender.sendMessage(ChatColor.GREEN + "Ignore list: " + message);
				}else {
					sender.sendMessage(ChatColor.RED + "0 players ignored");
				}
			}else {
				sender.sendMessage(ChatColor.RED + "0 players ignored");
			}
		}
		return true;
	}
}
