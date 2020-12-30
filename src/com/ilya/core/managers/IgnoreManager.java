package com.ilya.core.managers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.ilya.core.Main;

public class IgnoreManager {
	Main core;
	FileConfiguration file;
	
	public IgnoreManager(Main core) {
		File ignorefile = new File(core.getDataFolder() + File.separator + "ignore.yml");
		file = YamlConfiguration.loadConfiguration(ignorefile);;
		this.core = core;
	}
	
	public static List<String> ignoring = new ArrayList<String>();
	
	
	public List<String> getIgnoring() {
		return ignoring;
	}
	
	public void clear() {
		ignoring.clear();
	}
	
	public void add(Player player1) {
		ignoring.add(player1.getName());
	}
	
	public void set(String s) {
		file.set(s, ignoring);
	}
	
	public void set(String s, Object o) {
		file.set(s, o);
	}

	public Object get(String s) {
		return file.get(s);
	}
	
	public FileConfiguration getConfig() {
		return file;
	}
	
	public void saveConfig() {
		try {
			file.save(core.getIgnoreFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
