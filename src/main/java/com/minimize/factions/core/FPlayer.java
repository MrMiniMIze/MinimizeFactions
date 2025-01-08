package com.minimize.factions.core;

import org.bukkit.entity.Player;

/**
 * Represents a player's Factions membership
 * Also handles normal auto-claim and admin auto-claim
 * Author: minimize
 */
public class FPlayer {

    private final Player bukkitPlayer;
    private String factionId = null;

    // Normal auto-claim
    private boolean autoClaimOn = false;

    // Admin auto-claim overrides to forcibly claim for a given faction ID
    private String adminAutoClaimFor = null;

    public FPlayer(Player bukkitPlayer) {
        this.bukkitPlayer = bukkitPlayer;
    }

    public Player getBukkitPlayer() {
        return bukkitPlayer;
    }

    public String getFactionId() {
        return factionId;
    }

    public Faction getFaction() {
        if (factionId == null) return null;
        return Board.getInstance().getFactionById(factionId);
    }

    public void joinFaction(Faction faction) {
        this.factionId = faction.getId();
    }

    public void leaveFaction() {
        this.factionId = null;
    }

    // Normal auto-claim
    public boolean isAutoClaimOn() {
        return autoClaimOn;
    }

    public void setAutoClaimOn(boolean on) {
        this.autoClaimOn = on;
    }

    // Admin auto-claim
    public String getAdminAutoClaimFor() {
        return adminAutoClaimFor;
    }

    public void setAdminAutoClaimFor(String facId) {
        this.adminAutoClaimFor = facId;
    }
}
