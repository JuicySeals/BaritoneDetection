package me.juicyseals.Storage;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class FakeBlocks {
    public static List<Location> fakeBlocks = new ArrayList<>();

    public void addFakeBlock(int x, int y, int z) {
        fakeBlocks.add(new Location(Bukkit.getWorlds().get(0), x, y, z));
    }

    public void removeFakeBlock(int x, int y, int z) {
        fakeBlocks.remove(new Location(Bukkit.getWorlds().get(0), x, y, z));
    }

    public boolean isBlockFake(Location l) {
        return fakeBlocks.contains(l);
    }
}
