package com.minimize.factions.data;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Saves all faction data on player logout
 * Author: minimize
 */
public class FactionDataListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        FactionDataHelper.saveAll();
    }
}
