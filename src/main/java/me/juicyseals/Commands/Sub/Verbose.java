package me.juicyseals.Commands.Sub;

import me.juicyseals.BaritoneDetection;
import me.juicyseals.Interfaces.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Verbose implements SubCommand {
    private BaritoneDetection baritoneDetection;

    public Verbose(BaritoneDetection baritoneDetection) {
        this.baritoneDetection =  baritoneDetection;
    }
    @Override
    public void onCommand(CommandSender cmds, Command cmd, String s, String[] strings) {
        if(!(cmds instanceof Player)) {cmds.sendMessage(ChatColor.RED + ""); return;}
        Player p = (Player) cmds;
        baritoneDetection.staff.toggleVerbose(p);
    }

    @Override
    public String getName() {
        return "Verbose";
    }

    @Override
    public String getUsage() {
        return "/bd verbose";
    }

    @Override
    public String getPermission() {
        return "BaritoneDetection.Alerts";
    }

    @Override
    public String getDescription() {
        return "Shows all alerts/flags";
    }
}
