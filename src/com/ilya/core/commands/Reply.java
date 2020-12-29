package com.ilya.core.commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ilya.core.Main;

public class Reply implements CommandExecutor {
	Main core;
	
	public Reply(Main core) {
		this.core = core;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player1 = (Player) sender;
			if (Main.messageManager.getReplyTarget(player1) != null) {
				Player player2 = Main.messageManager.getReplyTarget(player1);
				if (core.getServer().getOfflinePlayer(player2.getName()).getPlayer() != null) {
					String message = "";
					for (String s : args) {
						message += s + " ";
					}
					sender.sendMessage(ChatColor.LIGHT_PURPLE + "To " + player2.getName() + ": " + message);
					List<String> ignoring = Main.ignoringManager.getConfig().getStringList("players." + player2.getName() + ".ignoring");
					if (ignoring.contains(sender.getName())) {
						sender.sendMessage(ChatColor.RED + sender.getName() + " ignores you, the message was not delivered");
						return true;
					}else {
						player2.sendMessage(ChatColor.LIGHT_PURPLE + sender.getName() + " whispers: " + message);
					}
				}else {
					sender.sendMessage(ChatColor.RED + "Player " + player2.getName() + " is offline!");
				}
			}else {
				sender.sendMessage(ChatColor.RED + "You have recived no whispers this session");
			}
		}
		return true;
	}
}
