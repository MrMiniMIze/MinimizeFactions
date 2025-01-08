package com.minimize.factions.cmd.claim;

import com.minimize.factions.config.Conf;
import com.minimize.factions.core.Board;
import com.minimize.factions.core.FPlayer;
import com.minimize.factions.core.FPlayers;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * /f unclaimall
 * Author: minimize
 */
public class CmdUnclaimAll {

    public boolean onCommand(Player player, String[] args) {
        // usage
        if (args.length > 1) {
            // no extra usage needed, we do it directly
        }

        FPlayer fp = FPlayers.getInstance().getByPlayer(player);
        if (fp.getFaction() == null) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Conf.msgUnclaimAllNoFaction));
            return true;
        }

        Board.getInstance().unclaimAll(fp.getFaction().getId());
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Conf.msgUnclaimAllSuccess));
        return true;
    }
}
