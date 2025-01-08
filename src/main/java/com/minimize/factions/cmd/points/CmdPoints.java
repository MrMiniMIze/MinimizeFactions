package com.minimize.factions.cmd.points;

import com.minimize.factions.config.Conf;
import com.minimize.factions.core.FPlayer;
import com.minimize.factions.core.FPlayers;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * /f points subcommand
 * Author: minimize
 */
public class CmdPoints {

    public boolean onCommand(Player player, String[] args) {
        // If no extra args => show current points
        if (args.length == 1) {
            FPlayer fp = FPlayers.getInstance().getByPlayer(player);
            if (fp.getFaction() == null) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Conf.msgPointsNoFaction));
                return true;
            }
            int pts = fp.getFaction().getPoints();
            String msg = Conf.msgPointsCurrent.replace("%points%", String.valueOf(pts));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
            return true;
        }

        if (args.length >= 2) {
            String sub = args[1].toLowerCase();
            FPlayer fp = FPlayers.getInstance().getByPlayer(player);
            if (fp.getFaction() == null) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Conf.msgPointsNoFaction));
                return true;
            }

            if (sub.equals("add")) {
                if (args.length < 3) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', Conf.msgPointsUsage));
                    return true;
                }
                try {
                    int amt = Integer.parseInt(args[2]);
                    fp.getFaction().addPoints(amt);
                    String msg = Conf.msgPointsAddSuccess.replace("%amount%", String.valueOf(amt));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
                } catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', Conf.msgPointsInvalidNumber));
                }
                return true;
            }

            if (sub.equals("set")) {
                if (args.length < 3) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', Conf.msgPointsUsage));
                    return true;
                }
                try {
                    int amt = Integer.parseInt(args[2]);
                    fp.getFaction().setPoints(amt);
                    String msg = Conf.msgPointsSetSuccess.replace("%amount%", String.valueOf(amt));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
                } catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', Conf.msgPointsInvalidNumber));
                }
                return true;
            }
        }

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Conf.msgPointsUsage));
        return true;
    }
}
