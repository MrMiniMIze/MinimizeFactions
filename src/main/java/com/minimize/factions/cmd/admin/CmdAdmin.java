package com.minimize.factions.cmd.admin;

import com.minimize.factions.config.Conf;
import com.minimize.factions.core.Board;
import com.minimize.factions.core.ChunkCoord;
import com.minimize.factions.core.FPlayer;
import com.minimize.factions.core.FPlayers;
import com.minimize.factions.core.Faction;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * /f admin subcommand
 * Admin overrides for claim/unclaim/unclaimall/autoclaim
 * Author: minimize
 */
public class CmdAdmin {

    public boolean onCommand(Player player, String[] args) {
        if (args.length < 2) {
            player.sendMessage(cc(Conf.msgAdminUsage));
            return true;
        }

        String sub = args[1].toLowerCase();

        // /f admin claim <factionName>
        if (sub.equals("claim")) {
            if (args.length < 3) {
                player.sendMessage(cc(Conf.msgAdminUsage));
                return true;
            }
            String targetFacName = args[2];
            Faction targetFaction = findFactionByTag(targetFacName);
            if (targetFaction == null) {
                String msg = Conf.msgAdminClaimNoFaction.replace("%name%", targetFacName);
                player.sendMessage(cc(msg));
                return true;
            }
            ChunkCoord cc = ChunkCoord.fromPlayer(player);
            Board.getInstance().setClaim(cc, targetFaction.getId());

            String msg = Conf.msgAdminClaimSuccess.replace("%faction%", targetFaction.getTag());
            player.sendMessage(cc(msg));
            return true;
        }

        // /f admin unclaim
        if (sub.equals("unclaim")) {
            ChunkCoord cc = ChunkCoord.fromPlayer(player);
            Board.getInstance().unclaim(cc);

            player.sendMessage(cc(Conf.msgAdminUnclaimSuccess));
            return true;
        }

        // /f admin unclaimall <factionName>
        if (sub.equals("unclaimall")) {
            if (args.length < 3) {
                player.sendMessage(cc(Conf.msgAdminUnclaimAllUsage));
                return true;
            }
            String facName = args[2];
            Faction targetFaction = findFactionByTag(facName);
            if (targetFaction == null) {
                String msg = Conf.msgAdminUnclaimAllNoFaction.replace("%name%", facName);
                player.sendMessage(cc(msg));
                return true;
            }
            Board.getInstance().unclaimAll(targetFaction.getId());
            String msg = Conf.msgAdminUnclaimAllSuccess.replace("%faction%", targetFaction.getTag());
            player.sendMessage(cc(msg));
            return true;
        }

        // /f admin autoclaim <factionName>
        if (sub.equals("autoclaim")) {
            if (args.length < 3) {
                player.sendMessage(cc(Conf.msgAdminAutoClaimUsage));
                return true;
            }
            String facName = args[2];
            Faction targetFaction = findFactionByTag(facName);
            if (targetFaction == null) {
                String msg = Conf.msgAdminAutoClaimNoFaction.replace("%name%", facName);
                player.sendMessage(cc(msg));
                return true;
            }

            FPlayer fplayer = FPlayers.getInstance().getByPlayer(player);
            if (fplayer.getAdminAutoClaimFor() == null) {
                // enable
                fplayer.setAdminAutoClaimFor(targetFaction.getId());
                String smsg = Conf.msgAdminAutoClaimOn.replace("%faction%", targetFaction.getTag());
                player.sendMessage(cc(smsg));
            } else {
                // disable
                fplayer.setAdminAutoClaimFor(null);
                player.sendMessage(cc(Conf.msgAdminAutoClaimOff));
            }
            return true;
        }

        // unknown
        player.sendMessage(cc(Conf.msgAdminUsage));
        return true;
    }

    private Faction findFactionByTag(String tag) {
        return Board.getInstance().getAllFactions().stream()
                .filter(f -> f.getTag().equalsIgnoreCase(tag))
                .findFirst()
                .orElse(null);
    }

    private String cc(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
