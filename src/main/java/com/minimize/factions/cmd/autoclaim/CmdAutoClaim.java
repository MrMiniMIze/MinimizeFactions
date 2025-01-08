package com.minimize.factions.cmd.autoclaim;

import com.minimize.factions.config.Conf;
import com.minimize.factions.core.FPlayer;
import com.minimize.factions.core.FPlayers;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * /f autoclaim toggles autoClaimOn for a player
 * Author: minimize
 */
public class CmdAutoClaim {

    private String cc(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    public boolean onCommand(Player player, String[] args) {
        FPlayer fp = FPlayers.getInstance().getByPlayer(player);
        if (fp.getFaction() == null) {
            // Must be in faction
            player.sendMessage(cc(Conf.msgClaimNoFaction));
            return true;
        }

        boolean current = fp.isAutoClaimOn();
        fp.setAutoClaimOn(!current);
        if (!current) {
            // turned on
            player.sendMessage(cc(Conf.msgAutoClaimEnabled));
        } else {
            // turned off
            player.sendMessage(cc(Conf.msgAutoClaimDisabled));
        }
        return true;
    }
}
