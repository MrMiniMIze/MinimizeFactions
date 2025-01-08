package com.minimize.factions.core;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Manages all FPlayer objects
 * Author: minimize
 */

public class FPlayers {

    private static final FPlayers instance = new FPlayers();
    public static FPlayers getInstance() { return instance; }

    private final Map<UUID, FPlayer> playerMap = new HashMap<>();

    public FPlayer getByPlayer(Player player) {
        return playerMap.computeIfAbsent(player.getUniqueId(), uuid -> new FPlayer(player));
    }
}
