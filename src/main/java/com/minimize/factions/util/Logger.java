package com.minimize.factions.util;

import org.bukkit.Bukkit;

/**
 * Simple logger for MinimizeFactions
 * Author: minimize
 */
public class Logger {

    public enum PrefixType {
        DEFAULT, WARNING, FAILED
    }

    public static void print(String msg, PrefixType prefix) {
        switch (prefix) {
            case DEFAULT:
                Bukkit.getLogger().info("[MinimizeFactions] " + msg);
                break;
            case WARNING:
                Bukkit.getLogger().warning("[MinimizeFactions] " + msg);
                break;
            case FAILED:
                Bukkit.getLogger().severe("[MinimizeFactions] " + msg);
                break;
        }
    }
}
