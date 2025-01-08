package com.minimize.factions.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.List;

/**
 * Loads plugin config and messages from config.yml
 * Author: minimize
 */
public class Conf {

    // Grace
    public static boolean graceEnabled = false;
    public static int graceMinutesRemaining = 120;

    // TNT / Points
    public static long tntMaxBank = 1000000L;
    public static int pointsMax = 999999;

    // territory block break
    public static boolean territoryBlockProtection = true;

    // root
    public static String msgRootHeader;
    public static List<String> msgRootList;
    public static String msgRootUnknownSubcmd;

    // create
    public static String msgCreateUsage;
    public static String msgCreateAlreadyInFaction;
    public static String msgCreateSuccess;

    // disband
    public static String msgDisbandNotInFaction;
    public static String msgDisbandSuccess;

    // points
    public static String msgPointsNoFaction;
    public static String msgPointsCurrent;
    public static String msgPointsUsage;
    public static String msgPointsAddSuccess;
    public static String msgPointsSetSuccess;
    public static String msgPointsInvalidNumber;

    // tnt
    public static String msgTntNoFaction;
    public static String msgTntCurrent;
    public static String msgTntUsage;
    public static String msgTntAddSuccess;
    public static String msgTntSetSuccess;
    public static String msgTntInvalidNumber;

    // check
    public static String msgCheck;

    // claim
    public static String msgClaimNoFaction;
    public static String msgClaimAlreadyClaimed;
    public static String msgClaimSuccess;
    public static String msgClaimUsage;

    // unclaim
    public static String msgUnclaimNoFaction;
    public static String msgUnclaimNotOwned;
    public static String msgUnclaimSuccess;
    public static String msgUnclaimUsage;

    // unclaimall
    public static String msgUnclaimAllNoFaction;
    public static String msgUnclaimAllSuccess;

    // block break
    public static String msgBlockCancelBreak;

    // autoclaim
    public static String msgAutoClaimEnabled;
    public static String msgAutoClaimDisabled;

    // map
    private static int mapRadius;
    private static String mapHeader;
    private static String mapFooter;
    private static String mapLineFormat;
    private static String mapWildernessSymbol;
    private static String mapYourSymbol;
    private static String mapOtherSymbol;

    // admin messages
    public static String msgAdminUsage;
    public static String msgAdminClaimNoFaction;
    public static String msgAdminClaimSuccess;
    public static String msgAdminUnclaimSuccess;
    public static String msgAdminUnclaimAllUsage;
    public static String msgAdminUnclaimAllNoFaction;
    public static String msgAdminUnclaimAllSuccess;
    public static String msgAdminAutoClaimUsage;
    public static String msgAdminAutoClaimNoFaction;
    public static String msgAdminAutoClaimOn;
    public static String msgAdminAutoClaimOff;

    public static void load(File dataFolder) {
        Plugin plugin = org.bukkit.Bukkit.getPluginManager().getPlugin("MinimizeFactions");
        if (plugin == null) return;

        plugin.saveDefaultConfig();
        FileConfiguration cfg = plugin.getConfig();

        // core
        graceEnabled = cfg.getBoolean("grace-system.enabled", false);
        graceMinutesRemaining = cfg.getInt("grace-system.minutes", 120);
        tntMaxBank = cfg.getLong("tnt.max-bank", 1000000L);
        pointsMax = cfg.getInt("points.max", 999999);
        territoryBlockProtection = cfg.getBoolean("territoryBlockProtection", true);

        // root
        msgRootHeader = cfg.getString("messages.root.header", "&eMinimizeFactions Commands:");
        msgRootList = cfg.getStringList("messages.root.list");
        msgRootUnknownSubcmd = cfg.getString("messages.root.unknown_subcommand", "&cUnknown subcommand: &e%subcmd%");

        // create
        msgCreateUsage = cfg.getString("messages.create.usage", "&cUsage: /f create <name>");
        msgCreateAlreadyInFaction = cfg.getString("messages.create.already_in_faction", "&cYou are already in a faction!");
        msgCreateSuccess = cfg.getString("messages.create.success", "&aCreated faction: &e%faction%");

        // disband
        msgDisbandNotInFaction = cfg.getString("messages.disband.not_in_faction", "&cYou are not in a faction!");
        msgDisbandSuccess = cfg.getString("messages.disband.success", "&eYour faction has been disbanded.");

        // points
        msgPointsNoFaction = cfg.getString("messages.points.no_faction", "&cYou are not in a faction!");
        msgPointsCurrent = cfg.getString("messages.points.current", "&6Your faction has &e%points% &6points.");
        msgPointsUsage = cfg.getString("messages.points.usage", "&eUsage: /f points [add|set] <amount>");
        msgPointsAddSuccess = cfg.getString("messages.points.add_success", "&aAdded &e%amount% &ato your faction's points.");
        msgPointsSetSuccess = cfg.getString("messages.points.set_success", "&aSet faction points to &e%amount%.");
        msgPointsInvalidNumber = cfg.getString("messages.points.invalid_number", "&cThat is not a valid number.");

        // tnt
        msgTntNoFaction = cfg.getString("messages.tnt.no_faction", "&cYou are not in a faction!");
        msgTntCurrent = cfg.getString("messages.tnt.current", "&6Faction TNT Bank: &e%tnt%");
        msgTntUsage = cfg.getString("messages.tnt.usage", "&eUsage: /f tnt [add|set] <amount>");
        msgTntAddSuccess = cfg.getString("messages.tnt.add_success", "&aAdded &e%amount% &aTNT to your faction.");
        msgTntSetSuccess = cfg.getString("messages.tnt.set_success", "&aSet TNT bank to &e%amount%.");
        msgTntInvalidNumber = cfg.getString("messages.tnt.invalid_number", "&cThat is not a valid number.");

        // check
        msgCheck = cfg.getString("messages.check.message", "&7No suspicious activity found.");

        // claim
        msgClaimNoFaction = cfg.getString("messages.claim.no_faction", "&cYou are not in a faction!");
        msgClaimAlreadyClaimed = cfg.getString("messages.claim.already_claimed", "&cThis chunk is already claimed by &f%owner%&c!");
        msgClaimSuccess = cfg.getString("messages.claim.success", "&aClaimed this chunk for your faction.");
        msgClaimUsage = cfg.getString("messages.claim.usage", "&cUsage: /f claim");

        // unclaim
        msgUnclaimNoFaction = cfg.getString("messages.unclaim.no_faction", "&cYou are not in a faction!");
        msgUnclaimNotOwned = cfg.getString("messages.unclaim.not_owned", "&cThis chunk is not owned by your faction.");
        msgUnclaimSuccess = cfg.getString("messages.unclaim.success", "&aUnclaimed this chunk.");
        msgUnclaimUsage = cfg.getString("messages.unclaim.usage", "&cUsage: /f unclaim");

        // unclaimall
        msgUnclaimAllNoFaction = cfg.getString("messages.unclaimall.no_faction", "&cYou are not in a faction!");
        msgUnclaimAllSuccess = cfg.getString("messages.unclaimall.success", "&aUnclaimed all your faction's land.");

        // block
        msgBlockCancelBreak = cfg.getString("messages.block.cancel_break", "&cYou cannot break blocks in the territory of &f%faction%&c!");

        // autoclaim
        msgAutoClaimEnabled = cfg.getString("messages.autoclaim.enabled", "&aAutoClaim is now &eenabled.");
        msgAutoClaimDisabled = cfg.getString("messages.autoclaim.disabled", "&cAutoClaim is now &edisabled.");

        // map
        mapRadius = cfg.getInt("messages.map.radius", 5);
        mapHeader = cfg.getString("messages.map.header", "&6--- Nearby Factions Map ---");
        mapFooter = cfg.getString("messages.map.footer", "&6--- End of Map ---");
        mapLineFormat = cfg.getString("messages.map.line_format", "&7%line%");
        mapWildernessSymbol = cfg.getString("messages.map.wilderness_symbol", "-");
        mapYourSymbol = cfg.getString("messages.map.your_symbol", "&aY");
        mapOtherSymbol = cfg.getString("messages.map.other_symbol", "&c#");

        // admin
        msgAdminUsage = cfg.getString("messages.admin.usage", "&cUsage: /f admin <claim|unclaim|unclaimall|autoclaim> ...");
        msgAdminClaimNoFaction = cfg.getString("messages.admin.claim.no_faction", "&cNo such faction: &f%name%");
        msgAdminClaimSuccess = cfg.getString("messages.admin.claim.success", "&aAdmin-claimed this chunk for faction &f%faction%&a.");
        msgAdminUnclaimSuccess = cfg.getString("messages.admin.unclaim.success", "&eAdmin unclaimed this chunk.");
        msgAdminUnclaimAllUsage = cfg.getString("messages.admin.unclaimall.usage", "&cUsage: /f admin unclaimall <factionName>");
        msgAdminUnclaimAllNoFaction = cfg.getString("messages.admin.unclaimall.no_faction", "&cNo such faction: &f%name%");
        msgAdminUnclaimAllSuccess = cfg.getString("messages.admin.unclaimall.success", "&eAdmin unclaimed all land for &f%faction%&e.");
        msgAdminAutoClaimUsage = cfg.getString("messages.admin.autoclaim.usage", "&cUsage: /f admin autoclaim <factionName>");
        msgAdminAutoClaimNoFaction = cfg.getString("messages.admin.claim.no_faction", "&cNo such faction: &f%name%");
        msgAdminAutoClaimOn = cfg.getString("messages.admin.autoclaim.success_on", "&aAdmin auto-claim &eenabled &afor faction &f%faction%&a.");
        msgAdminAutoClaimOff = cfg.getString("messages.admin.autoclaim.success_off", "&cAdmin auto-claim &edisabled.");
    }

    // map getters
    public static int mapRadius() { return mapRadius; }
    public static String msgMapHeader() { return mapHeader; }
    public static String msgMapFooter() { return mapFooter; }
    public static String mapLineFormat() { return mapLineFormat; }
    public static String mapWildernessSymbol() { return mapWildernessSymbol; }
    public static String mapYourSymbol() { return mapYourSymbol; }
    public static String mapOtherSymbol() { return mapOtherSymbol; }
}
