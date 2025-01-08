package com.minimize.factions.data;

import org.bukkit.Bukkit;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages all FactionData objects in memory
 * Author: minimize
 */
public class FactionDataHelper {

    private static final Map<String, FactionData> dataCache = new HashMap<>();

    public static void init() {
        File dir = DataFolderProvider.getFactionDataFolder();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        Bukkit.getLogger().info("[MinimizeFactions] FactionDataHelper initialized.");
    }

    public static FactionData getData(String factionId) {
        return dataCache.computeIfAbsent(factionId, FactionData::new);
    }

    public static void removeData(String factionId) {
        dataCache.remove(factionId);
        File file = new File(DataFolderProvider.getFactionDataFolder(), factionId + ".yml");
        if (file.exists()) file.delete();
    }

    public static void saveAll() {
        for (FactionData fd : dataCache.values()) {
            fd.save();
        }
    }
}
