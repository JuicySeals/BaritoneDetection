package me.juicyseals.Commands.Sub;

import me.juicyseals.BaritoneDetection;
import me.juicyseals.Interfaces.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class Help implements SubCommand {
    BaritoneDetection baritoneDetection;

    public Help(BaritoneDetection baritoneDetection) {
        this.baritoneDetection = baritoneDetection;
    }

    @Override
    public void onCommand(CommandSender cmds, Command cmd, String s, String[] strings) {
        cmds.sendMessage(ChatColor.GRAY + "---------------|" + ChatColor.GOLD + " Commands " + ChatColor.GRAY + "|---------------");
        for (SubCommand scmd : baritoneDetection.commandHandler.getSubCommands()) {
            cmds.sendMessage(ChatColor.YELLOW + scmd.getUsage() + ChatColor.GOLD + " | " + ChatColor.GRAY + scmd.getDescription());
        }
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getUsage() {
        return "/bd help";
    }

    @Override
    public String getPermission() {
        return "BaritoneDetection.Help";
    }

    @Override
    public String getDescription() {
        return "Shows commands";
    }
}
