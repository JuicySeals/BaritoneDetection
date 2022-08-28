package me.juicyseals.Storage;

import me.juicyseals.BaritoneDetection;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Staff {
    List<Player> staffsAlertsOff = new ArrayList<>();
    List<Player> staffVerbose = new ArrayList<>();

    public boolean isAlertsOn(Player p) {
        return !staffsAlertsOff.contains(p);
    }

    public void toggleAlerts(Player p) {
        if(staffsAlertsOff.contains(p)) {
            staffsAlertsOff.remove(p);
            p.sendMessage(BaritoneDetection.prefix + ChatColor.GREEN + "Alerts turned off");
        }else {
            staffsAlertsOff.add(p);
            p.sendMessage(BaritoneDetection.prefix + ChatColor.RED + "Alerts turned on");
        }
    }

    public void toggleVerbose(Player p) {
        if(staffVerbose.contains(p)) {
            staffVerbose.remove(p);
            p.sendMessage(BaritoneDetection.prefix + ChatColor.GREEN + "Verbose alerts turned off");
        }else {
            staffVerbose.add(p);
            p.sendMessage(BaritoneDetection.prefix + ChatColor.RED + "Verbose alerts turned on");
        }
    }
}
