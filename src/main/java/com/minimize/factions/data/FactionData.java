package com.minimize.factions.data;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Per-faction YAML data
 * Author: minimize
 */
public class FactionData {

    private final String factionID;
    private final Map<String, Object> dataMap = new HashMap<>();
    private boolean saving = false;

    public FactionData(String factionID) {
        this.factionID = factionID;
        load();
    }

    public void load() {
        File file = getFile();
        if (!file.exists()) return;

        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        for (String key : config.getKeys(false)) {
            dataMap.put(key, config.get(key));
        }
    }

    public void save() {
        if (saving) return;
        saving = true;

        File file = getFile();
        YamlConfiguration config = new YamlConfiguration();
        for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
            config.set(entry.getKey(), entry.getValue());
        }
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        saving = false;
    }

    private File getFile() {
        return new File(DataFolderProvider.getFactionDataFolder(), factionID + ".yml");
    }

    public Object get(String key) {
        return dataMap.get(key);
    }

    public void set(String key, Object value) {
        dataMap.put(key, value);
    }

    public String getFactionID() {
        return factionID;
    }
}
