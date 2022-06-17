package me.yailya.servercore;

import me.yailya.servercore.commands.*;
import me.yailya.servercore.events.*;
import me.yailya.servercore.managers.IgnoreManager;
import me.yailya.servercore.managers.MessageManager;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin implements CommandExecutor, Listener {
    public static long startTime;
    public static MessageManager messageManager;
    public static IgnoreManager ignoreManager;
    private static Main instance;
    private final File ignoreFile = new File(getDataFolder() + File.separator + "Ignore.yml");

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void onEnable() {
        instance = this;

        messageManager = new MessageManager();
        ignoreManager = new IgnoreManager();

        if (!new File(getDataFolder() + File.separator + "config.yml").exists()) {
            getLogger().info("Creating config file..");
            getConfig().options().copyDefaults();
            saveDefaultConfig();
        }

        getCommand("discord").setExecutor(new Discord());
        getCommand("stats").setExecutor(new Stats());
        getCommand("message").setExecutor(new Message());
        getCommand("reply").setExecutor(new Reply());
        getCommand("ignore").setExecutor(new Ignore());
        getCommand("ignorelist").setExecutor(new IgnoreList());
        getCommand("uptime").setExecutor(new Uptime());
        getCommand("ping").setExecutor(new Ping());
        getCommand("bed").setExecutor(new Bed());
        getCommand("joindate").setExecutor(new JoinDate());
        getCommand("kill").setExecutor(new Kill());

        new EventPlayerJoinLeave();
        new EventPlayerChat();
        new EventPlayerInventory();
        new EventBlockPlace();
        new EventEntityHit();

        if (!ignoreFile.exists()) {
            try {
                ignoreFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        startTime = System.currentTimeMillis();
        getLogger().info("ServerCore enabled!");
    }

    public void onDisable() {
        getLogger().info("ServerCore disabled!");
    }

    public File getIgnoreFile() {
        return ignoreFile;
    }

    public static Main getInstance() {
        return instance;
    }

    public static void registerListener(Listener listener) {
        Main.getInstance().getServer().getPluginManager().registerEvents(listener, Main.getInstance());
    }
}
