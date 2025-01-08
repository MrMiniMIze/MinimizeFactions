package com.minimize.factions.cmd;

import com.minimize.factions.cmd.autoclaim.CmdAutoClaim;
import com.minimize.factions.cmd.check.CmdCheck;
import com.minimize.factions.cmd.claim.CmdClaim;
import com.minimize.factions.cmd.claim.CmdUnclaim;
import com.minimize.factions.cmd.claim.CmdUnclaimAll;
import com.minimize.factions.cmd.map.CmdMap;
import com.minimize.factions.cmd.points.CmdPoints;
import com.minimize.factions.cmd.tnt.CmdTnt;
import com.minimize.factions.config.Conf;
import com.minimize.factions.core.Board;
import com.minimize.factions.core.FPlayer;
import com.minimize.factions.core.FPlayers;
import com.minimize.factions.util.TextUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.minimize.factions.cmd.admin.CmdAdmin;

/**
 * /f root command
 * Author: minimize
 */
public class CmdFactions implements CommandExecutor {

    private final CmdPoints cmdPoints = new CmdPoints();
    private final CmdTnt cmdTnt = new CmdTnt();
    private final CmdCheck cmdCheck = new CmdCheck();
    private final CmdClaim cmdClaim = new CmdClaim();
    private final CmdUnclaim cmdUnclaim = new CmdUnclaim();
    private final CmdUnclaimAll cmdUnclaimAll = new CmdUnclaimAll();
    private final CmdAutoClaim cmdAutoClaim = new CmdAutoClaim();
    private final CmdMap cmdMap = new CmdMap();
    private final CmdAdmin cmdAdmin = new CmdAdmin();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Conf.msgRootHeader));
            for (String line : Conf.msgRootList) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', line));
            }
            return true;
        }

        String sub = args[0].toLowerCase();

        // points
        if (sub.equals("points")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Players only.");
                return true;
            }
            return cmdPoints.onCommand((Player) sender, args);
        }

        // tnt
        if (sub.equals("tnt")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Players only.");
                return true;
            }
            return cmdTnt.onCommand((Player) sender, args);
        }

        // check
        if (sub.equals("check")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Players only.");
                return true;
            }
            return cmdCheck.onCommand((Player) sender, args);
        }

        // create
        if (sub.equals("create")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Players only.");
                return true;
            }
            Player player = (Player) sender;
            FPlayer fp = FPlayers.getInstance().getByPlayer(player);

            if (fp.getFaction() != null) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Conf.msgCreateAlreadyInFaction));
                return true;
            }
            if (args.length < 2) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Conf.msgCreateUsage));
                return true;
            }

            String facName = TextUtil.implode(args, 1, " ");
            Board.getInstance().createFaction(facName);

            String msg = Conf.msgCreateSuccess.replace("%faction%", facName);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
            return true;
        }

        // disband
        if (sub.equals("disband")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Players only.");
                return true;
            }
            Player player = (Player) sender;
            FPlayer fp = FPlayers.getInstance().getByPlayer(player);
            if (fp.getFaction() == null) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Conf.msgDisbandNotInFaction));
                return true;
            }
            fp.getFaction().disband();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Conf.msgDisbandSuccess));
            return true;
        }

        // claim
        if (sub.equals("claim")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Players only.");
                return true;
            }
            return cmdClaim.onCommand((Player) sender, args);
        }

        // unclaim
        if (sub.equals("unclaim")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Players only.");
                return true;
            }
            return cmdUnclaim.onCommand((Player) sender, args);
        }

        // unclaimall
        if (sub.equals("unclaimall")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Players only.");
                return true;
            }
            return cmdUnclaimAll.onCommand((Player) sender, args);
        }

        // autoclaim
        if (sub.equals("autoclaim")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Players only.");
                return true;
            }
            return cmdAutoClaim.onCommand((Player) sender, args);
        }

        // map
        if (sub.equals("map")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Players only.");
                return true;
            }
            return cmdMap.onCommand((Player) sender, args);
        }

        // admin
        if (sub.equals("admin")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Players only.");
                return true;
            }
            if (!sender.hasPermission("factions.admin")) {
                sender.sendMessage(ChatColor.RED + "You lack permission: factions.admin");
                return true;
            }
            return cmdAdmin.onCommand((Player) sender, args);
        }

        // unknown
        String msgUnknown = Conf.msgRootUnknownSubcmd.replace("%subcmd%", sub);
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', msgUnknown));
        return true;
    }
}
