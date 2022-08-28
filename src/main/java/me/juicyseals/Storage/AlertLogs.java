package me.juicyseals.Storage;

import me.juicyseals.Interfaces.Check;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class AlertLogs {
    HashMap<Player, HashMap<Check, Integer>> logs = new HashMap<>();

    public int getFlagAmount(Player p, Check c) {
        if (!logs.containsKey(p)) {
            logs.put(p, new HashMap<>());
        }
        if (logs.get(p).containsKey(c)) {
            return logs.get(p).get(c);
        } else {
            logs.get(p).put(c, 0);
            return 0;
        }
    }

    public void addFlag(Player p, Check c) {
        logs.get(p).put(c, logs.get(p).get(c) + 1);
    }

    public void resetFlags(Player p, Check c) {
        logs.get(p).put(c, 0);
    }

    public void resetFlags(Player p) {
        logs.get(p).clear();
    }
}
