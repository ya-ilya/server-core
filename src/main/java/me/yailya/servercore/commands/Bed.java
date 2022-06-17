package me.yailya.servercore.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Bed implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(org.bukkit.ChatColor.RED + "Only players can use this command!");

            return true;
        }

        Location bed = ((Player) sender).getBedSpawnLocation();

        if (bed != null) {
            sender.sendMessage(
                    ChatColor.GOLD + "Your bed location: " + ChatColor.GREEN
                            + bed.getBlockX() + "x "
                            + bed.getBlockY() + "y "
                            + bed.getBlockZ() + "z"
            );
        } else {
            sender.sendMessage(ChatColor.RED + "You don't have a bed!");
        }

        return true;
    }
}
