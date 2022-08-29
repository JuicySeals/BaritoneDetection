package me.juicyseals.Commands.Sub;

import me.juicyseals.BaritoneDetection;
import me.juicyseals.Interfaces.SubCommand;
import me.juicyseals.Storage.Log;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Logs implements SubCommand {
    private BaritoneDetection baritoneDetection;
    public Logs(BaritoneDetection baritoneDetection) {
        this.baritoneDetection = baritoneDetection;
    }
    @Override
    public void onCommand(CommandSender cmds, Command cmd, String s, String[] strings) {
        if(strings.length != 2) {cmds.sendMessage(ChatColor.RED + "Wrong amount of args"); return;}
        cmds.sendMessage(ChatColor.GREEN + "Getting logs of " + strings[1]);
        Log log = baritoneDetection.db.getLogs(Bukkit.getOfflinePlayer(strings[1]).getUniqueId().toString());
        cmds.sendMessage(ChatColor.GRAY + "Logs for " + ChatColor.GOLD + strings[1]);
        cmds.sendMessage(" ");
        List<String> checkName = new ArrayList<>();
        for(Map.Entry<String, Integer> set: log.ToHashMap().entrySet()) {
            checkName.add(set.getKey());
        }
        Collections.sort(checkName);
        for(String check : checkName) {
            cmds.sendMessage(ChatColor.GRAY + "- " + ChatColor.GOLD + check + " (" + ChatColor.YELLOW + log.ToHashMap().get(check) + ChatColor.GOLD + ")");
        }
    }

    @Override
    public String getName() {
        return "logs";
    }

    @Override
    public String getUsage() {
        return "/bd logs <player>";
    }

    @Override
    public String getPermission() {
        return "BaritoneDetection.Logs";
    }

    @Override
    public String getDescription() {
        return "Shows logs for a player";
    }
}
