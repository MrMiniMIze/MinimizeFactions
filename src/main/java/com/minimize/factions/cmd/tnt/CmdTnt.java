package com.minimize.factions.cmd.tnt;

import com.minimize.factions.config.Conf;
import com.minimize.factions.core.FPlayer;
import com.minimize.factions.core.FPlayers;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * /f tnt subcommand
 * Author: minimize
 */
public class CmdTnt {

    public boolean onCommand(Player player, String[] args) {
        // /f tnt => show TNT
        if (args.length == 1) {
            FPlayer fp = FPlayers.getInstance().getByPlayer(player);
            if (fp.getFaction() == null) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Conf.msgTntNoFaction));
                return true;
            }
            long tnt = fp.getFaction().getTnt();
            String msg = Conf.msgTntCurrent.replace("%tnt%", String.valueOf(tnt));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
            return true;
        }

        // /f tnt [add|set] <amount>
        if (args.length >= 2) {
            String sub = args[1].toLowerCase();
            FPlayer fp = FPlayers.getInstance().getByPlayer(player);
            if (fp.getFaction() == null) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Conf.msgTntNoFaction));
                return true;
            }

            if (sub.equals("add")) {
                if (args.length < 3) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', Conf.msgTntUsage));
                    return true;
                }
                try {
                    long amt = Long.parseLong(args[2]);
                    fp.getFaction().addTnt(amt);
                    String msg = Conf.msgTntAddSuccess.replace("%amount%", String.valueOf(amt));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
                } catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', Conf.msgTntInvalidNumber));
                }
                return true;
            }

            if (sub.equals("set")) {
                if (args.length < 3) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', Conf.msgTntUsage));
                    return true;
                }
                try {
                    long amt = Long.parseLong(args[2]);
                    fp.getFaction().setTnt(amt);
                    String msg = Conf.msgTntSetSuccess.replace("%amount%", String.valueOf(amt));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
                } catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', Conf.msgTntInvalidNumber));
                }
                return true;
            }
        }

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Conf.msgTntUsage));
        return true;
    }
}
