package com.ilya.core.commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ilya.core.Main;

public class Message implements CommandExecutor {
	Main core;
	
	public Message(Main core) {
		this.core = core;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player && args.length > 0) {
			if (core.getServer().getOfflinePlayer(args[0]).getPlayer() != null) {
				Player player1 = (Player) sender;
				Player player2 = core.getServer().getOfflinePlayer(args[0]).getPlayer();
				Main.messageManager.setReplyTarget(player1, player2);
				String message = "";
				args[0] = "";
				for (String s : args) {
					message += s + " ";
				}
				sender.sendMessage(ChatColor.LIGHT_PURPLE + "To " + player2.getName() + ":" + message);
				List<String> ignoring = Main.ignoreManager.getConfig().getStringList("players." + player2.getName() + ".ignoring");
				if (ignoring.contains(sender.getName())) {
					sender.sendMessage(ChatColor.RED + sender.getName() + " ignores you, the message was not delivered");
					return true;
				}else {
					player2.sendMessage(ChatColor.LIGHT_PURPLE + sender.getName() + " whispers:" + message);
				}
			}else {
				sender.sendMessage(ChatColor.RED + "Player " + args[0] + " cannot be found!");
			}
		}else {
			sender.sendMessage(ChatColor.RED + "Usage: /tell <player> <private message ...>");
		}
		return true;
	}
}
