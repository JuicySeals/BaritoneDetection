package me.juicyseals.Storage;

import me.juicyseals.Alert;
import me.juicyseals.BaritoneDetection;
import me.juicyseals.Database.Database;
import me.juicyseals.Interfaces.Check;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class AlertLogs {
    public BaritoneDetection baritoneDetection;
    public AlertLogs(BaritoneDetection baritoneDetection) {
        this.baritoneDetection = baritoneDetection;
    }
    HashMap<Player, HashMap<Check, Integer>> logs = new HashMap<>();

    public int getFlagAmount(Player p, Check c) {
       return baritoneDetection.db.getFlagCount(p, c);
    }

    public void addFlag(Player p, Check c) {
        baritoneDetection.db.addAlert(c, p);
    }

    public void resetFlags(Player p, Check c) {
        baritoneDetection.db.resetFlags(p, c);
    }

    public void resetFlags(Player p) {
        baritoneDetection.db.resetFlags(p);
    }
}
