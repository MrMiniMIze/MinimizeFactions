package com.minimize.factions.data;

import com.minimize.factions.MinimizeFactionsPlugin;
import java.io.File;

/**
 * Provides folder for faction .yml data
 * Author: minimize
 */
public class DataFolderProvider {

    public static File getFactionDataFolder() {
        return new File(MinimizeFactionsPlugin.getInstance().getDataFolder(), "faction-data");
    }
}
