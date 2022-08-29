package me.juicyseals.Storage;

import me.juicyseals.Alert;
import me.juicyseals.BaritoneDetection;
import me.juicyseals.Database.Database;
import me.juicyseals.Interfaces.Check;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class AlertLogs {
    public BaritoneDetection baritoneDetection;
    public AlertLogs(BaritoneDetection baritoneDetection) {
        this.baritoneDetection = baritoneDetection;
    }
    public void resetFlags(UUID UUID) {
        baritoneDetection.db.resetFlags(UUID);
    }
}
