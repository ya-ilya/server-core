package me.yailya.servercore.commands;

import me.yailya.servercore.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.util.Objects;

public class Stats implements CommandExecutor {
    static long folderSize(File directory) {
        long length = 0;

        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.isFile()) {
                length += file.length();
            } else {
                length += folderSize(file);
            }
        }

        return length;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        sender.sendMessage(
                ChatColor.GOLD + "-----------------------------------------------------\n" + ChatColor.GREEN
                        + "World size - " + (int) folderSize(Main.getInstance().getServer().getWorldContainer()) / (1024 * 1024) + "MB\n"
                        + "Unique joins - " + Main.getInstance().getServer().getOfflinePlayers().length
                        + ChatColor.GOLD + "\n-----------------------------------------------------"
        );

        return true;
    }
}
