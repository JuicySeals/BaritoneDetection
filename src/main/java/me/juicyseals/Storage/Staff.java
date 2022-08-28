package me.juicyseals.Storage;

import me.juicyseals.BaritoneDetection;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Staff {
    public static List<Player> staffsAlertsOff = new ArrayList<>();
    List<Player> staffVerbose = new ArrayList<>();

    public void toggleAlerts(Player p) {
        if(staffsAlertsOff.contains(p)) {
            staffsAlertsOff.remove(p);
            p.sendMessage(BaritoneDetection.prefix + ChatColor.GREEN + "Enabled alerts");
        }else {
            staffsAlertsOff.add(p);
            p.sendMessage(BaritoneDetection.prefix + ChatColor.RED + "Disabled alerts");
        }
    }
}
