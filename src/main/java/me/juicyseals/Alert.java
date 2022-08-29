package me.juicyseals;

import me.juicyseals.Commands.Sub.Alerts;
import me.juicyseals.Interfaces.Check;
import me.juicyseals.Storage.Staff;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Alert {
    static BaritoneDetection main;
    public Alert(BaritoneDetection baritoneDetection) {
        main = baritoneDetection;
    }
    private static HashMap<Player, Integer> flagCount = new HashMap<>();
    public static void alert(Check check, Player p) {
        if(!isAlertReady(p)) {return;}
        TextComponent alert = new TextComponent(ChatColor.GRAY + "[" + ChatColor.YELLOW + "!" + ChatColor.GRAY + "] " + check.getCheckName() + " was flagged by " + p.getDisplayName());
        alert.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GRAY + "Yaw: " + p.getLocation().getYaw() + "\nPitch: " + p.getLocation().getPitch() + "\nPing: " + p.getPing() + "\nSeverity: " + check.getSeverity().color + check.getSeverity()).create()));
        for(Player staffp :Bukkit.getOnlinePlayers()) {
            if(!staffp.hasPermission("BaritoneDetection.Alert")) {continue;}
            if(!Staff.staffsAlertsOff.contains(staffp)) {
                staffp.spigot().sendMessage(alert);
            }
        }
        main.db.addAlert(check, p);
    }

    private static boolean isAlertReady(Player p) {
        if(!flagCount.containsKey(p)) { flagCount.put(p, 1); return false;}
        if(flagCount.get(p) <= 2) {
            flagCount.put(p, flagCount.get(p)+1);
            return false;
        }else {
            flagCount.put(p, 0);
            return true;
        }
    }
}
