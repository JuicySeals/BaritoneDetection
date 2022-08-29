package me.juicyseals.Commands.Sub;

import io.sentry.Sentry;
import me.juicyseals.BaritoneDetection;
import me.juicyseals.Interfaces.SubCommand;
import me.juicyseals.Util.UUIDFetcher;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ResetLogs implements SubCommand {
    private BaritoneDetection baritoneDetection;

    public ResetLogs(BaritoneDetection baritoneDetection) {
        this.baritoneDetection = baritoneDetection;
    }
    @Override
    public void onCommand(CommandSender cmds, Command cmd, String s, String[] strings) {
        try {
            if(strings.length != 2) {cmds.sendMessage(ChatColor.RED + "Wrong amount of args"); return;}
            baritoneDetection.db.resetFlags(UUIDFetcher.getUUID(strings[1]));
            cmds.sendMessage(BaritoneDetection.prefix + ChatColor.GREEN + "Cleared logs of " + strings[1]);
        }catch (Exception e) {
            Sentry.captureException(e);
        }
    }

    @Override
    public String getName() {
        return "resetlogs";
    }

    @Override
    public String getUsage() {
        return "/bd resetlogs <player>";
    }

    @Override
    public String getPermission() {
        return "BaritoneDetection.ResetLogs";
    }

    @Override
    public String getDescription() {
        return "Resets a players logs";
    }
}
