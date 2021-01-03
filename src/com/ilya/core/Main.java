package com.ilya.core;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.ilya.core.commands.Bed;
import com.ilya.core.commands.Discord;
import com.ilya.core.commands.Ignore;
import com.ilya.core.commands.IgnoreList;
import com.ilya.core.commands.JoinDate;
import com.ilya.core.commands.Kill;
import com.ilya.core.commands.Message;
import com.ilya.core.commands.Ping;
import com.ilya.core.commands.Reply;
import com.ilya.core.commands.Stats;
import com.ilya.core.commands.Uptime;
import com.ilya.core.events.EventBlockPlace;
import com.ilya.core.events.EventEntityHit;
import com.ilya.core.events.EventPlayerChat;
import com.ilya.core.events.EventPlayerInventory;
import com.ilya.core.events.EventPlayerJoinLeave;
import com.ilya.core.managers.IgnoreManager;
import com.ilya.core.managers.MessageManager;
import com.ilya.core.managers.PlayerManager;

public class Main extends JavaPlugin implements CommandExecutor, Listener {
	public static long startTime;
	private static Main instance;
	public static MessageManager messageManager;
	public static IgnoreManager ignoreManager;
	public static PlayerManager playerManager;
	File ignorefile = new File(getDataFolder() + File.separator + "ignore.yml");
	File joindatesfile = new File(getDataFolder() + File.separator + "joindates.yml");
	public static Main getInstance(){
		return instance;
	}
	
	
	public void onEnable() {
		messageManager = new MessageManager(this);
		ignoreManager = new IgnoreManager(this);
		playerManager = new PlayerManager(this);
		
		instance = this;
		
		File config = new File(getDataFolder() + File.separator + "config.yml");
		if (!config.exists()) {
			getLogger().info("Creating config file..");
			getConfig().options().copyDefaults();
			saveDefaultConfig();
		}
		
		getCommand("discord").setExecutor(new Discord(this));
		getCommand("stats").setExecutor(new Stats(this));
		getCommand("message").setExecutor(new Message(this));
		getCommand("reply").setExecutor(new Reply(this));
		getCommand("ignore").setExecutor(new Ignore(this));
		getCommand("ignorelist").setExecutor(new IgnoreList(this));
		getCommand("uptime").setExecutor(new Uptime(this));
		getCommand("ping").setExecutor(new Ping(this));
		getCommand("bed").setExecutor(new Bed(this));
		getCommand("joindate").setExecutor(new JoinDate(this));
		getCommand("kill").setExecutor(new Kill(this));
		
		new EventPlayerJoinLeave(this);
		new EventPlayerChat(this);
		new EventPlayerInventory(this);
		new EventBlockPlace(this);
		new EventEntityHit(this);
		
		if (!ignorefile.exists()) {
			try {
				ignorefile.createNewFile();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		if (!joindatesfile.exists()) {
			try {
				joindatesfile.createNewFile();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		startTime = System.currentTimeMillis();
		getLogger().info("ServerCore enabled!");
	}
	
	public void onDisable() {
		getLogger().info("ya-ilya core disabled!");
	}
	
	public File getIgnoreFile() {
		return ignorefile;
	}
	
	public File getJoinDatesFile() {
		return joindatesfile;
	}
}
