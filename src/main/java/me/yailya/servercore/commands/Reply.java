package me.yailya.servercore.commands;

import me.yailya.servercore.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Reply implements CommandExecutor {
    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");

            return true;
        }

        Player to = Main.messageManager.getReplyTarget((Player) sender);

        if (to != null) {
            if (Main.getInstance().getServer().getOfflinePlayer(to.getName()).getPlayer() != null) {
                Message.sendPrivateMessage(sender, to, args);
            } else {
                sender.sendMessage(ChatColor.RED + "Player " + to.getName() + " is offline!");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "You have received no whispers this session");
        }

        return true;
    }
}
