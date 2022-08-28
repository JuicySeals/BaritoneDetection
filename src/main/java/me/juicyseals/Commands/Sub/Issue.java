package me.juicyseals.Commands.Sub;

import com.google.common.base.Joiner;
import me.juicyseals.BaritoneDetection;
import me.juicyseals.Feedback;
import me.juicyseals.Interfaces.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class Issue implements SubCommand {

    @Override
    public void onCommand(CommandSender cmds, Command cmd, String s, String[] strings) {
        if(strings[1] == null) {
            cmds.sendMessage(ChatColor.RED + "Wrong amount of args");
            return;
        }

        String comment = Joiner.on(" ").join(strings);
        Feedback.issue(cmds.getName(), comment);
        cmds.sendMessage(BaritoneDetection.prefix + ChatColor.GREEN + "Sumitted issue.");
    }

    @Override
    public String getName() {
        return "issue";
    }

    @Override
    public String getUsage() {
        return "/bd issue <issue>";
    }

    @Override
    public String getPermission() {
        return "BaritoneDetection.Issue";
    }

    @Override
    public String getDescription() {
        return "Reports an issue";
    }
}
