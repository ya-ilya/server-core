package me.yailya.servercore.commands;

import me.yailya.servercore.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Message implements CommandExecutor {
    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");

            return true;
        }

        if (args.length > 0) {
            Player to = Main.getInstance().getServer().getOfflinePlayer(args[0]).getPlayer();

            if (to != null) {
                Main.messageManager.setReplyTarget((Player) sender, to);
                sendPrivateMessage(sender, to, args);
            } else {
                sender.sendMessage(ChatColor.RED + "Player " + args[0] + " not found!");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Usage: /" + label + " <player> <private message ...>");
        }

        return true;
    }

    public static void sendPrivateMessage(CommandSender sender, Player to, String[] args) {
        StringBuilder message = new StringBuilder();
        args[0] = "";

        for (String s : args) {
            message.append(s).append(" ");
        }

        if (Main.ignoreManager.getIgnoreList(to.getName()).contains(sender.getName())) {
            sender.sendMessage(ChatColor.RED + sender.getName() + " ignores you, the message was not delivered");
        } else {
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "To " + to.getName() + ":" + message);
            to.sendMessage(ChatColor.LIGHT_PURPLE + sender.getName() + " whispers:" + message);
        }
    }
}
