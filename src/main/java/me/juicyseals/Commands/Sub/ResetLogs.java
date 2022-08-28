package me.juicyseals.Commands.Sub;

import io.sentry.Sentry;
import me.juicyseals.BaritoneDetection;
import me.juicyseals.Interfaces.SubCommand;
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
            if(Bukkit.getPlayer(strings[1]) == null) {cmds.sendMessage(ChatColor.RED + "Invalid player"); return;}
            Player p = Bukkit.getPlayer(strings[1]);
            baritoneDetection.alertLogs.resetFlags(p);
            p.sendMessage(BaritoneDetection.prefix + p.getDisplayName() + " logs cleared.");
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
