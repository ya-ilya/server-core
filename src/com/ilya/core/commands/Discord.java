package com.ilya.core.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.ilya.core.Main;

public class Discord implements CommandExecutor {
	Main core;
	
	public Discord(Main core) {
		this.core = core;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		sender.sendMessage(ChatColor.GOLD + "Join in the discord: " + ChatColor.GREEN + core.getConfig().getString("discord-invite"));
		return true;
	}
}
