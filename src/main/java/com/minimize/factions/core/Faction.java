package com.minimize.factions.core;

import com.minimize.factions.config.Conf;
import com.minimize.factions.data.FactionData;
import com.minimize.factions.data.FactionDataHelper;
import com.minimize.factions.util.Logger;

/**
 * Represents a Faction with points & TNT bank
 * Author: minimize
 */
public class Faction {

    private final String id;
    private String tag;
    private int points = 0;
    private long tnt = 0;

    public Faction(String id, String tag) {
        this.id = id;
        this.tag = tag;
    }

    public String getId() { return id; }
    public String getTag() { return tag; }
    public void setTag(String newTag) { this.tag = newTag; }

    // Points
    public int getPoints() {
        return points;
    }

    public void addPoints(int amount) {
        points += amount;
        if (points > Conf.pointsMax) {
            points = Conf.pointsMax;
        }
    }

    public void setPoints(int newVal) {
        points = Math.min(newVal, Conf.pointsMax);
    }

    // TNT
    public long getTnt() {
        return tnt;
    }

    public void addTnt(long amount) {
        tnt += amount;
        if (tnt > Conf.tntMaxBank) {
            tnt = Conf.tntMaxBank;
        }
    }

    public void setTnt(long amt) {
        tnt = Math.min(amt, Conf.tntMaxBank);
    }

    public FactionData getData() {
        return FactionDataHelper.getData(this.id);
    }

    public void disband() {
        Board.getInstance().removeFaction(this);
        Logger.print("Disbanded faction [" + tag + "]", Logger.PrefixType.DEFAULT);
    }
}
