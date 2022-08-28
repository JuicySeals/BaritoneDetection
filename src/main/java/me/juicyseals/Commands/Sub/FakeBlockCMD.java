package me.juicyseals.Commands.Sub;

import io.sentry.Sentry;
import me.juicyseals.BaritoneDetection;
import me.juicyseals.Interfaces.SubCommand;
import me.juicyseals.Storage.FakeBlocks;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FakeBlockCMD implements SubCommand {
    FakeBlocks fakeBlocks;
    BaritoneDetection baritoneDetection;

    public FakeBlockCMD(BaritoneDetection baritoneDetection) {
        this.fakeBlocks = baritoneDetection.fakeBlocks;
        this.baritoneDetection = baritoneDetection;
    }

    @Override
    public void onCommand(CommandSender cmds, Command cmd, String s, String[] strings) {
        try {
            if (strings.length != 3) {
                cmds.sendMessage(ChatColor.RED + "Wrong amount of args");
                return;
            }
            if (Bukkit.getPlayer(strings[1]) == null) {
                cmds.sendMessage(ChatColor.RED + "Invalid player");
                return;
            }
            if (Material.matchMaterial(strings[2]) == null) {
                cmds.sendMessage(ChatColor.RED + "Invalid block");
                return;
            }

            Player p = Bukkit.getPlayer(strings[1]);
            Location l = p.getLocation().add(p.getLocation().getDirection()).add(0, 1, 0);
            Material block = Material.matchMaterial(strings[2]);
            Material blockBefore = l.getBlock().getType();

            fakeBlocks.addFakeBlock(l.getBlockX(), l.getBlockY(), l.getBlockZ());
            l.getBlock().setType(block);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(baritoneDetection, new Runnable() {
                public void run() {
                    l.getBlock().setType(blockBefore);
                    fakeBlocks.removeFakeBlock(l.getBlockX(), l.getBlockY(), l.getBlockZ());
                }
            }, 5L);

        }catch (Exception e) {
            Sentry.captureException(e);
        }
    }

    @Override
    public String getName() {
        return "fake";
    }

    @Override
    public String getUsage() {
        return "/bd fake <player> <block>";
    }

    @Override
    public String getPermission() {
        return "BaritoneDetection.FakeBlock";
    }

    @Override
    public String getDescription() {
        return "Tests if user is using baritone by spawning block";
    }
}
