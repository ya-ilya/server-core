package com.ilya.core.commands;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.ilya.core.Main;

public class Stats implements CommandExecutor {
	Main core;
	
	public Stats(Main core) {
		this.core = core;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		int players = core.getServer().getOfflinePlayers().length;
		int worldsize = (int) folderSize(core.getServer().getWorldContainer()) / (1024 * 1024);
		sender.sendMessage(ChatColor.GOLD + "-----------------------------------------------------\n" + ChatColor.GREEN + "World size - " + worldsize + "MB\n" + "Unique joins - " + players + ChatColor.GOLD + "\n-----------------------------------------------------");
		return true;
	}
	
	static long folderSize(File directory) {
	    long length = 0;
	    for (File file : directory.listFiles()) {
	        if (file.isFile())
	            length += file.length();
	        else
	            length += folderSize(file);
	    }
	    return length;
	}
}
