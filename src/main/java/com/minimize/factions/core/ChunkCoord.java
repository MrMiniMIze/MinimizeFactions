package com.minimize.factions.core;

import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Represents the chunk coordinates (chunkX, chunkZ, world).
 * Author: minimize
 */
public class ChunkCoord {
    private final String world;
    private final int x;
    private final int z;

    public ChunkCoord(String world, int x, int z) {
        this.world = world;
        this.x = x;
        this.z = z;
    }

    public String getWorld() {
        return world;
    }

    public int getX() {
        return x;
    }

    public int getZ() {
        return z;
    }

    public static ChunkCoord fromPlayer(Player p) {
        return fromLocation(p.getLocation());
    }

    public static ChunkCoord fromLocation(Location loc) {
        return new ChunkCoord(loc.getWorld().getName(), loc.getChunk().getX(), loc.getChunk().getZ());
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ChunkCoord)) return false;
        ChunkCoord cc = (ChunkCoord) o;
        return world.equals(cc.world) && x == cc.x && z == cc.z;
    }

    @Override
    public int hashCode() {
        return (world + x + "_" + z).hashCode();
    }
}
