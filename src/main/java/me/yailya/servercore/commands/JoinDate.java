package me.yailya.servercore.commands;

import me.yailya.servercore.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Date;

@SuppressWarnings("deprecation")
public class JoinDate implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");

            return true;
        }

        if (args.length > 0) {
            Player player = Main.getInstance().getServer().getOfflinePlayer(args[0]).getPlayer();

            if (player != null) {
                sender.sendMessage(ChatColor.GOLD + player.getName() + "'s join date: " + ChatColor.GREEN + new Date(player.getFirstPlayed()));
            } else {
                sender.sendMessage(ChatColor.RED + args[0] + " never joined in to the server");
            }
        } else {
            sender.sendMessage(ChatColor.GOLD + "Your join date: " + ChatColor.GREEN + new Date(((Player) sender).getFirstPlayed()));
        }

        return true;
    }
}
