package com.minimize.factions.listeners;

import com.minimize.factions.config.Conf;
import com.minimize.factions.core.Board;
import com.minimize.factions.core.ChunkCoord;
import com.minimize.factions.core.FPlayer;
import com.minimize.factions.core.FPlayers;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

/**
 * If territoryBlockProtection = true, block break if chunk is claimed by another faction
 * Author: minimize
 */
public class FactionsBlockListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (!Conf.territoryBlockProtection) return;

        FPlayer fp = FPlayers.getInstance().getByPlayer(event.getPlayer());
        if (fp == null) return;

        ChunkCoord cc = ChunkCoord.fromLocation(event.getBlock().getLocation());
        if (!Board.getInstance().isClaimed(cc)) return; // not claimed, free to break

        String ownerId = Board.getInstance().getOwner(cc);
        // If it's the same faction, allow break
        if (fp.getFaction() != null && fp.getFaction().getId().equals(ownerId)) {
            return;
        }

        // else it's owned by a different faction
        String factionName = Board.getInstance().getFactionById(ownerId).getTag();
        String msg = Conf.msgBlockCancelBreak.replace("%faction%", factionName);
        event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
        event.setCancelled(true);
    }
}
