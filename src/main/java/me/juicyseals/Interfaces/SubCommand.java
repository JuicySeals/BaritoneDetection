package me.juicyseals.Interfaces;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface SubCommand {
    void onCommand(CommandSender cmds, Command cmd, String s, String[] strings);

    String getName();

    String getUsage();

    String getPermission();

    String getDescription();
}
