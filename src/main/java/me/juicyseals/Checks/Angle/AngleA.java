package me.juicyseals.Checks.Angle;

import io.sentry.Sentry;
import me.juicyseals.Alert;
import me.juicyseals.BaritoneDetection;
import me.juicyseals.Interfaces.Check;
import me.juicyseals.Severity;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class AngleA implements Check, Listener {
    private final BaritoneDetection baritoneDetection;

    public AngleA(BaritoneDetection baritoneDetection) {
        this.baritoneDetection = baritoneDetection;
    }

    @Override
    public String getCheckName() {
        return "AngleA";
    }

    @Override
    public Severity getSeverity() {
        return Severity.LOW;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e)
    {
        try {
            Player p = e.getPlayer();
            float pitch = p.getLocation().getPitch();
            if (pitch >= 25.8 && pitch <= 26.2) {
                    Alert.alert(this, p);
            }
        }catch (Exception exception) {
            Sentry.captureException(exception);
        }

    }
}
