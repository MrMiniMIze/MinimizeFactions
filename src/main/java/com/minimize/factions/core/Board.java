package com.minimize.factions.core;

import com.minimize.factions.data.FactionDataHelper;
import com.minimize.factions.util.Logger;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Holds references to all loaded factions + chunk claims
 * Author: minimize
 */
public class Board {

    private static final Board instance = new Board();
    public static Board getInstance() { return instance; }

    private final Map<String, Faction> factionsById = new HashMap<>();
    private final Map<ChunkCoord, String> claims = new HashMap<>();

    public Faction createFaction(String tag) {
        String newId = "fac_" + System.currentTimeMillis();
        Faction faction = new Faction(newId, tag);
        factionsById.put(newId, faction);
        Logger.print("Created faction [" + tag + "] ID=" + newId, Logger.PrefixType.DEFAULT);
        return faction;
    }

    public Faction getFactionById(String id) {
        return factionsById.get(id);
    }

    public Collection<Faction> getAllFactions() {
        return factionsById.values();
    }

    public void removeFaction(Faction faction) {
        factionsById.remove(faction.getId());
        claims.entrySet().removeIf(e -> e.getValue().equals(faction.getId()));

        FactionDataHelper.removeData(faction.getId());
        Logger.print("Removed faction " + faction.getId(), Logger.PrefixType.DEFAULT);
    }

    public boolean isClaimed(ChunkCoord coord) {
        return claims.containsKey(coord);
    }

    public String getOwner(ChunkCoord coord) {
        return claims.get(coord);
    }

    public void setClaim(ChunkCoord coord, String factionId) {
        claims.put(coord, factionId);
    }

    public void unclaim(ChunkCoord coord) {
        claims.remove(coord);
    }

    public void unclaimAll(String factionId) {
        claims.entrySet().removeIf(e -> e.getValue().equals(factionId));
    }
}
