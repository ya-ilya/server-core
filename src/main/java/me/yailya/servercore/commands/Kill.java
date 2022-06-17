package me.yailya.servercore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kill implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");

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
