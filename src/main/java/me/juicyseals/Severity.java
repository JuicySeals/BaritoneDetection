package me.juicyseals;

import org.bukkit.ChatColor;

public enum Severity {
    LOW(ChatColor.GREEN),
    MEDIUM(ChatColor.YELLOW),
    HIGH(ChatColor.RED),
    CERTAIN(ChatColor.DARK_RED);

    public final ChatColor color;

    Severity(ChatColor color) {
        this.color = color;
    }
}
