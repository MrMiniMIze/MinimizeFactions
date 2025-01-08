package com.minimize.factions.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 * If you want to block /home in enemy territory, or more
 * Author: minimize
 */
public class PlayerCommandPreprocessListener implements Listener {

    @EventHandler
    public void onPreProcess(PlayerCommandPreprocessEvent event) {
        // Optionally intercept commands
    }
}
