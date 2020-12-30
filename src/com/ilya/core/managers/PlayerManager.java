package com.ilya.core.managers;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.ilya.core.Main;

public class PlayerManager {
	Main core;
	FileConfiguration file;
	
	public PlayerManager(Main core) {
		File ignorefile = new File(core.getDataFolder() + File.separator + "joindates.yml");
		file = YamlConfiguration.loadConfiguration(ignorefile);;
		this.core = core;
	}
	
	public FileConfiguration getConfig() {
		return file;
	}
	
	public void saveConfig() {
		try {
			file.save(core.getJoinDatesFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
