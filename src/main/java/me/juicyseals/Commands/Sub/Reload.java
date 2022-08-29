package me.juicyseals.Commands.Sub;

import me.juicyseals.BaritoneDetection;
import me.juicyseals.Interfaces.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class Reload implements SubCommand {
    private BaritoneDetection baritoneDetection;
    public Reload(BaritoneDetection baritoneDetection) {
        this.baritoneDetection = baritoneDetection;
    }
    @Override
    public void onCommand(CommandSender cmds, Command cmd, String s, String[] strings) {
        baritoneDetection.reloadConfig();
        BaritoneDetection.prefix = ChatColor.translateAlternateColorCodes('&', baritoneDetection.getConfig().getString("prefix"));
        cmds.sendMessage(BaritoneDetection.prefix + ChatColor.GREEN + "Reloaded config");
    }

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getUsage() {
        return "/bd reload";
    }

    @Override
    public String getPermission() {
        return "BaritoneDetection.Reload";
    }

    @Override
    public String getDescription() {
        return "Reloads config";
    }
}
