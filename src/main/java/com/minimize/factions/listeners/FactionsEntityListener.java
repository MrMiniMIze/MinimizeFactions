package com.minimize.factions.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Manage PVP checks or friendly fire
 * Author: minimize
 */
public class FactionsEntityListener implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        // e.g. block damage if same faction
    }
}
