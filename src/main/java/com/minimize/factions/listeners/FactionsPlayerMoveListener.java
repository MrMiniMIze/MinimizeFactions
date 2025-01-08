package com.minimize.factions.listeners;

import com.minimize.factions.core.Board;
import com.minimize.factions.core.ChunkCoord;
import com.minimize.factions.core.FPlayer;
import com.minimize.factions.core.FPlayers;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Handles auto-claiming for normal or admin usage
 * Author: minimize
 */
public class FactionsPlayerMoveListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (event.getTo() == null) return;
        if (event.getFrom().getChunk().equals(event.getTo().getChunk())) return;

        FPlayer fp = FPlayers.getInstance().getByPlayer(event.getPlayer());
        if (fp == null) return;

        // Admin auto-claim?
        String adminFacId = fp.getAdminAutoClaimFor();
        if (adminFacId != null) {
            ChunkCoord cc = ChunkCoord.fromLocation(event.getTo());
            if (!Board.getInstance().isClaimed(cc)) {
                Board.getInstance().setClaim(cc, adminFacId);
            }
            return;
        }

        // Normal auto-claim
        if (fp.isAutoClaimOn() && fp.getFaction() != null) {
            ChunkCoord cc = ChunkCoord.fromLocation(event.getTo());
            if (!Board.getInstance().isClaimed(cc)) {
                Board.getInstance().setClaim(cc, fp.getFaction().getId());
            }
        }
    }
}
