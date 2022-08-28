package me.juicyseals.Checks.Misc;

import io.sentry.Sentry;
import me.juicyseals.Alert;
import me.juicyseals.BaritoneDetection;
import me.juicyseals.Interfaces.Check;
import me.juicyseals.Severity;
import me.juicyseals.Storage.FakeBlocks;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class FakeBlockA implements Check, Listener {
    FakeBlocks fakeBlocks;

    public FakeBlockA(BaritoneDetection baritoneDetection) {
        this.fakeBlocks = baritoneDetection.fakeBlocks;
    }

    @Override
    public String getCheckName() {
        return "FakeBlockA";
    }

    @Override
    public Severity getSeverity() {
        return Severity.CERTAIN;
    }

    @EventHandler
    public void blockBreak(PlayerInteractEvent e) {
        try {
            if (e.getAction() != Action.LEFT_CLICK_BLOCK) {
                return;
            }
            if (fakeBlocks.isBlockFake(e.getClickedBlock().getLocation())) {
                Alert.alert(this, e.getPlayer());
            }
        }catch (Exception exception) {
            Sentry.captureException(exception);
        }

    }
}
