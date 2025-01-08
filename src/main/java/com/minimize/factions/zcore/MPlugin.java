package com.minimize.factions.zcore;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Minimal MPlugin from MassiveCore style
 * Author: minimize
 */
public abstract class MPlugin extends JavaPlugin {

    protected boolean loadSuccessful = false;
    private boolean autoSave = true;

    @Override
    public void onDisable() {
        if (loadSuccessful) {
            postAutoSave();
        }
    }

    public boolean preEnable() {
        // initial checks
        return true;
    }

    public void postEnable() {
        // finalize
    }

    public boolean getAutoSave() {
        return autoSave;
    }

    public void setAutoSave(boolean val) {
        this.autoSave = val;
    }

    public void postAutoSave() {
        // save if needed
    }

    public abstract com.google.gson.Gson getGson();

    public boolean handleCommand(CommandSender sender, String commandString, boolean testOnly) {
        return false;
    }
}
