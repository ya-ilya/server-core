package me.yailya.servercore.managers;

import me.yailya.servercore.Main;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IgnoreManager {
    private final YamlConfiguration configuration;

    public IgnoreManager() {
        configuration = YamlConfiguration.loadConfiguration(Main.getInstance().getIgnoreFile());
    }

    public YamlConfiguration getConfig() {
        return configuration;
    }

    public void saveConfig() {
        try {
            configuration.save(Main.getInstance().getIgnoreFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getIgnoreList(String name) {
        List<String> ignoreList = configuration.getStringList("players." + name + ".ignoring");

        if (ignoreList == null) {
            return new ArrayList<>();
        } else {
            return ignoreList;
        }
    }
}
