package me.juicyseals.Checks.Bridging;

import io.sentry.Sentry;
import me.juicyseals.Alert;
import me.juicyseals.BaritoneDetection;
import me.juicyseals.Interfaces.Check;
import me.juicyseals.Severity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BridgeA implements Check, Listener {
    BaritoneDetection baritoneDetection;

    public BridgeA(BaritoneDetection baritoneDetection) {
        this.baritoneDetection = baritoneDetection;
    }

    @Override
    public String getCheckName() {
        return "BridgeA";
    }

    @Override
    public Severity getSeverity() {
        return Severity.MEDIUM;
    }

    @EventHandler
    public void onWaterBucketPlace(BlockPlaceEvent e) {
        try {
            double pitch = e.getPlayer().getLocation().getPitch();
            Player p = e.getPlayer();
            if (pitch > 84.3 && pitch < 84.8) {
                if (baritoneDetection.alertLogs.getFlagAmount(p, this) == 2) {
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
