package me.juicyseals.Commands.Sub;

import com.google.common.base.Joiner;
import me.juicyseals.BaritoneDetection;
import me.juicyseals.Feedback;
import me.juicyseals.Interfaces.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class Suggest implements SubCommand {

    @Override
    public void onCommand(CommandSender cmds, Command cmd, String s, String[] strings) {
        if(strings[1] == null) {
            cmds.sendMessage(ChatColor.RED + "Wrong amount of args");
            return;
        }
        String comment = Joiner.on(" ").join(strings);
        Feedback.suggest(cmds.getName(), comment);
        cmds.sendMessage(BaritoneDetection.prefix + ChatColor.GREEN + "Sumitted suggestion.");
    }

    @Override
    public String getName() {
        return "suggest";
    }

    @Override
    public String getUsage() {
        return "/bd suggest <suggestion>";
    }

    @Override
    public String getPermission() {
        return "BaritoneDetection.Suggest";
    }

    @Override
    public String getDescription() {
        return "Suggests a feature";
    }
}
