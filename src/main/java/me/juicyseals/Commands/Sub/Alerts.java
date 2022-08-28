package me.juicyseals.Commands.Sub;

import me.juicyseals.Alert;
import me.juicyseals.BaritoneDetection;
import me.juicyseals.Interfaces.SubCommand;
import me.juicyseals.Storage.Staff;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Alerts implements SubCommand {
    BaritoneDetection baritoneDetection;

    public Alerts(BaritoneDetection baritoneDetection) {
        this.baritoneDetection = baritoneDetection;
    }
    @Override
    public void onCommand(CommandSender cmds, Command cmd, String s, String[] strings) {
        if(!(cmds instanceof Player)) {cmds.sendMessage(ChatColor.RED + "No permission."); return;}
        Player p = (Player) cmds;
        baritoneDetection.staff.toggleAlerts(p);

    }

    @Override
    public String getName() {
        return "alerts";
    }

    @Override
    public String getUsage() {
        return "/bd alerts";
    }

    @Override
    public String getPermission() {
        return "BaritoneDetection.Alerts";
    }

    @Override
    public String getDescription() {
        return "Toggles alerts on/off";
    }
}
