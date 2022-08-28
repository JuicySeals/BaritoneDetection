package me.juicyseals.Database;

import me.juicyseals.BaritoneDetection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.net.http.WebSocket;

public class PlayerJoin implements Listener {
    private BaritoneDetection baritoneDetection;
    public PlayerJoin(BaritoneDetection baritoneDetection) {
        this.baritoneDetection = baritoneDetection;
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        if(!e.getPlayer().hasPlayedBefore()) {
            baritoneDetection.db.initPlayer(e.getPlayer());
        }
    }
}
