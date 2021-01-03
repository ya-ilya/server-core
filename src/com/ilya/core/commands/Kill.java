package com.ilya.core.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ilya.core.Main;

public class Kill implements CommandExecutor {
	Main core;
	
	public Kill(Main core) {
		this.core = core;
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player p = (Player) sender;
		if (p.getHealth() <= 0 || p.isDead()) {
			return true;
		}
		p.setHealth(0);
		return true;
	}
}
