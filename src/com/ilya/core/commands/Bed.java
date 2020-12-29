package com.ilya.core.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ilya.core.Main;

import net.md_5.bungee.api.ChatColor;

public class Bed implements CommandExecutor{
	Main core;
	
	public Bed(Main core) {
		this.core = core;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (p.getBedSpawnLocation() != null) {
				Location bed = p.getBedSpawnLocation();
				sender.sendMessage(ChatColor.GOLD + "Your bed location: " + ChatColor.GREEN + bed.getBlockX() + "x " + bed.getBlockY() + "y " + bed.getBlockZ() + "z");
			}else {
				sender.sendMessage(ChatColor.RED + "You do not have a bed set!");
			}
		}
		return true;
	}
}
