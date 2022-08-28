package me.juicyseals.Checks.Angle;

import io.sentry.Sentry;
import me.juicyseals.Alert;
import me.juicyseals.BaritoneDetection;
import me.juicyseals.Interfaces.Check;
import me.juicyseals.Severity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class AngleD implements Check, Listener {
    BaritoneDetection baritoneDetection;

    public AngleD(BaritoneDetection baritoneDetection) {
        this.baritoneDetection = baritoneDetection;
    }

    @Override
    public String getCheckName() {
        return "AngleD";
    }

    @Override
    public Severity getSeverity() {
        return Severity.LOW;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        try {
            float pitch = e.getPlayer().getLocation().getPitch();
            Player p = e.getPlayer();
            if (pitch >= 85 && pitch < 85.2 || (pitch >= 87.3 && pitch < 87.8)) {
                if (baritoneDetection.alertLogs.getFlagAmount(p, this) == 3) {
                    Alert.alert(this, p);
                    baritoneDetection.alertLogs.resetFlags(p, this);
                } else {
                    baritoneDetection.alertLogs.addFlag(p, this);
                }
            }
        }catch (Exception exception) {
            Sentry.captureException(exception);
        }
    }
}
