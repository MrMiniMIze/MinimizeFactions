package com.minimize.factions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.minimize.factions.cmd.CmdFactions;
import com.minimize.factions.config.Conf;
import com.minimize.factions.config.GraceTask;
import com.minimize.factions.data.FactionDataHelper;
import com.minimize.factions.data.FactionDataListener;
import com.minimize.factions.listeners.FactionsBlockListener;
import com.minimize.factions.listeners.FactionsChatListener;
import com.minimize.factions.listeners.FactionsEntityListener;
import com.minimize.factions.listeners.PlayerCommandPreprocessListener;
import com.minimize.factions.listeners.FactionsPlayerMoveListener;
import com.minimize.factions.util.Logger;
import com.minimize.factions.zcore.MPlugin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import java.lang.reflect.Modifier;

/**
 * Main plugin class for MinimizeFactions
 * Author: minimize
 */
public class MinimizeFactionsPlugin extends MPlugin {

    private static MinimizeFactionsPlugin instance;

    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .disableHtmlEscaping()
            .enableComplexMapKeySerialization()
            .excludeFieldsWithModifiers(Modifier.TRANSIENT, Modifier.VOLATILE)
            .create();

    public static MinimizeFactionsPlugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        Logger.print("=== Enabling MinimizeFactions ===", Logger.PrefixType.DEFAULT);

        if (!preEnable()) {
            this.loadSuccessful = false;
            return;
        }

        // Load config
        Conf.load(this.getDataFolder());

        // Initialize data system
        FactionDataHelper.init();

        // Register events
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new FactionDataListener(), this);
        pm.registerEvents(new FactionsBlockListener(), this);
        pm.registerEvents(new FactionsChatListener(), this);
        pm.registerEvents(new FactionsEntityListener(), this);
        pm.registerEvents(new PlayerCommandPreprocessListener(), this);
        pm.registerEvents(new FactionsPlayerMoveListener(), this);


        // Grace system
        if (Conf.graceEnabled) {
            long interval = 20L * 60L; // 1 minute
            Bukkit.getScheduler().runTaskTimer(this, new GraceTask(), interval, interval);
            Logger.print("Grace system: " + Conf.graceMinutesRemaining + " minutes remain.", Logger.PrefixType.DEFAULT);
        }

        // Root command
        if (this.getCommand("factions") != null) {
            this.getCommand("factions").setExecutor(new CmdFactions());
        }

        postEnable();
        this.loadSuccessful = true;
        Logger.print("=== MinimizeFactions is enabled! ===", Logger.PrefixType.DEFAULT);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        Logger.print("=== MinimizeFactions has been disabled ===", Logger.PrefixType.DEFAULT);
    }

    @Override
    public Gson getGson() {
        return gson;
    }
}
