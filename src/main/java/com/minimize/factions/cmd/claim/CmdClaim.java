package com.minimize.factions.cmd.claim;

import com.minimize.factions.config.Conf;
import com.minimize.factions.core.Board;
import com.minimize.factions.core.ChunkCoord;
import com.minimize.factions.core.FPlayer;
import com.minimize.factions.core.FPlayers;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * /f claim
 * Author: minimize
 */
public class CmdClaim {

    public boolean onCommand(Player player, String[] args) {
        // /f claim
        if (args.length > 1) {
            // we only handle /f claim with no extra
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Conf.msgClaimUsage));
            return true;
        }

        FPlayer fp = FPlayers.getInstance().getByPlayer(player);
        if (fp.getFaction() == null) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Conf.msgClaimNoFaction));
            return true;
        }

        ChunkCoord cc = ChunkCoord.fromPlayer(player);
        if (Board.getInstance().isClaimed(cc)) {
            String ownerId = Board.getInstance().getOwner(cc);
            if (!ownerId.equals(fp.getFaction().getId())) {
                // claimed by another faction
                // We can get that faction's name:
                String factionName = Board.getInstance().getFactionById(ownerId).getTag();
                String msg = Conf.msgClaimAlreadyClaimed.replace("%owner%", factionName);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
                return true;
            } else {
                // It's already claimed by your faction
                String msg = Conf.msgClaimAlreadyClaimed.replace("%owner%", fp.getFaction().getTag());
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
                return true;
            }
        }

        // claim
        Board.getInstance().setClaim(cc, fp.getFaction().getId());
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Conf.msgClaimSuccess));
        return true;
    }
}
