package com.minimize.factions.cmd.claim;

import com.minimize.factions.config.Conf;
import com.minimize.factions.core.Board;
import com.minimize.factions.core.ChunkCoord;
import com.minimize.factions.core.FPlayer;
import com.minimize.factions.core.FPlayers;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * /f unclaim
 * Author: minimize
 */
public class CmdUnclaim {

    public boolean onCommand(Player player, String[] args) {
        if (args.length > 1) {
            // usage
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Conf.msgUnclaimUsage));
            return true;
        }

        FPlayer fp = FPlayers.getInstance().getByPlayer(player);
        if (fp.getFaction() == null) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Conf.msgUnclaimNoFaction));
            return true;
        }

        ChunkCoord cc = ChunkCoord.fromPlayer(player);
        if (!Board.getInstance().isClaimed(cc)) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Conf.msgUnclaimNotOwned));
            return true;
        }

        String ownerId = Board.getInstance().getOwner(cc);
        if (!ownerId.equals(fp.getFaction().getId())) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Conf.msgUnclaimNotOwned));
            return true;
        }

        Board.getInstance().unclaim(cc);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Conf.msgUnclaimSuccess));
        return true;
    }
}
