package com.minimize.factions.cmd.check;

import com.minimize.factions.config.Conf;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * /f check
 * Author: minimize
 */
public class CmdCheck {

    public boolean onCommand(Player player, String[] args) {
        String msg = ChatColor.translateAlternateColorCodes('&', Conf.msgCheck);
        player.sendMessage(msg);
        return true;
    }
}
