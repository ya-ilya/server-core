package me.yailya.servercore.commands;

import me.yailya.servercore.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Ping implements CommandExecutor {
    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length > 0) {
            Player player = Main.getInstance().getServer().getOfflinePlayer(args[0]).getPlayer();

            if (player != null) {
                if (player.isOnline()) {
                    sender.sendMessage(ChatColor.GOLD + player.getName() + "`s ping is " + ChatColor.GREEN + getPing(player));
                } else {
                    sender.sendMessage(ChatColor.RED + args[0] + " not online.");
                }
            }
        } else {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            } else {
                sender.sendMessage(ChatColor.GOLD + "Your ping is " + ChatColor.GREEN + getPing((Player) sender));
            }
        }

        return true;
    }

    public int getPing(Player player) {
        try {
            Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);

            return (int) entityPlayer.getClass().getField("ping").get(entityPlayer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
