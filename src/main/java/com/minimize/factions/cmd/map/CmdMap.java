package com.minimize.factions.cmd.map;

import com.minimize.factions.config.Conf;
import com.minimize.factions.core.Board;
import com.minimize.factions.core.ChunkCoord;
import com.minimize.factions.core.FPlayer;
import com.minimize.factions.core.FPlayers;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * /f map shows a text-based map of nearby chunks
 * Author: minimize
 */
public class CmdMap {

    public boolean onCommand(Player player, String[] args) {
        // radius from config?
        int radius = 5;
        if (Conf.mapRadius() > 0) {
            radius = Conf.mapRadius();
        }

        FPlayer fp = FPlayers.getInstance().getByPlayer(player);

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Conf.msgMapHeader()));

        int playerChunkX = player.getLocation().getChunk().getX();
        int playerChunkZ = player.getLocation().getChunk().getZ();
        String world = player.getWorld().getName();

        for (int dz = -radius; dz <= radius; dz++) {
            StringBuilder line = new StringBuilder();
            for (int dx = -radius; dx <= radius; dx++) {
                ChunkCoord cc = new ChunkCoord(world, playerChunkX + dx, playerChunkZ + dz);
                if (Board.getInstance().isClaimed(cc)) {
                    String ownerId = Board.getInstance().getOwner(cc);
                    if (fp != null && fp.getFaction() != null && ownerId.equals(fp.getFaction().getId())) {
                        // your faction
                        line.append(ChatColor.translateAlternateColorCodes('&', Conf.mapYourSymbol()));
                    } else {
                        // enemy or other faction
                        line.append(ChatColor.translateAlternateColorCodes('&', Conf.mapOtherSymbol()));
                    }
                } else {
                    // wilderness
                    line.append(Conf.mapWildernessSymbol());
                }
            }
            // Output line
            String lineFmt = Conf.mapLineFormat().replace("%line%", line.toString());
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', lineFmt));
        }

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Conf.msgMapFooter()));
        return true;
    }
}
