package me.juicyseals.Commands;

import io.sentry.Sentry;
import me.juicyseals.BaritoneDetection;
import me.juicyseals.Commands.Sub.Help;
import me.juicyseals.Interfaces.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler implements CommandExecutor {
    BaritoneDetection baritoneDetection;
    ArrayList<SubCommand> subCommands = new ArrayList<>();

    public CommandHandler(BaritoneDetection baritoneDetection) {
        this.baritoneDetection = baritoneDetection;
    }

    @Override
    public boolean onCommand(CommandSender a, Command b, String c, String[] d) {
            if (d.length < 1) {
                new Help(baritoneDetection).onCommand(a, b, c, d);
                return true;
            }
            for (SubCommand cmd : subCommands) {
                if (cmd.getName().equals(d[0])) {
                    if (a.hasPermission(cmd.getPermission()) && !cmd.getPermission().equals("default")) {
                        cmd.onCommand(a, b, c, d);
                        return true;
                    } else {
                        a.sendMessage(ChatColor.RED + "No permission.");
                    }
                }
            }
            return true;
    }


    public void registerSubCommand(SubCommand subCommand) {
        subCommands.add(subCommand);
    }

    public List<SubCommand> getSubCommands() {
        return subCommands;
    }
}
