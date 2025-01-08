package com.minimize.factions.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Faction chat or ally chat can be handled here
 * Author: minimize
 */
public class FactionsChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        // Potentially handle faction chat
    }
}
