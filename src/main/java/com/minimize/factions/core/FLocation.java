package com.minimize.factions.core;

/**
 * Minimal placeholder for territory coords
 * Author: minimize
 */
public class FLocation {
    private final String world;
    private final int x;
    private final int z;

    public FLocation(String world, int x, int z) {
        this.world = world;
        this.x = x;
        this.z = z;
    }

    public String getWorld() { return world; }
    public int getX() { return x; }
    public int getZ() { return z; }
}
