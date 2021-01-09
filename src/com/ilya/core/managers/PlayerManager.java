package com.ilya.core.managers;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.ilya.core.Main;
import org.bukkit.entity.Player;

public class PlayerManager {
	Main core;
	FileConfiguration file;
	
	public PlayerManager(Main core) {
		File JoinDatesFile = new File(core.getDataFolder() + File.separator + "JoinDates.yml");
		file = YamlConfiguration.loadConfiguration(JoinDatesFile);
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

	public String getJoinDate(Player player) {
		return getConfig().getString("players." + player.getName() + ".joinDate");
	}
}
