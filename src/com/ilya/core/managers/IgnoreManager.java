package com.ilya.core.managers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.ilya.core.Main;

public class IgnoreManager {
	Main core;
	FileConfiguration file;
	
	public IgnoreManager(Main core) {
		File IgnoreFile = new File(core.getDataFolder() + File.separator + "IgnoreList.yml");
		file = YamlConfiguration.loadConfiguration(IgnoreFile);
		this.core = core;
	}
	
	public static List<String> ignoring = new ArrayList<>();
	
	
	public List<String> getIgnoring() {
		return ignoring;
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
