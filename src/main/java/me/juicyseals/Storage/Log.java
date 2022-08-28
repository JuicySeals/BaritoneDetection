package me.juicyseals.Storage;

import java.util.HashMap;

public class Log {
    int AngleA;
    int AngleB;
    int AngleC;
    int AngleD;
    int BridgeA;

    public int getAngleA() {
        return AngleA;
    }

    public int getAngleB() {
        return AngleB;
    }

    public int getAngleC() {
        return AngleC;
    }

    public int getAngleD() {
        return AngleD;
    }

    public int getBridgeA() {
        return BridgeA;
    }

    public int getFakeBlockA() {
        return FakeBlockA;
    }

    int FakeBlockA;
    public Log(int AngleA, int AngleB, int AngleC, int AngleD, int BridgeA, int FakeBlockA) {
        this.AngleA = AngleA;
        this.AngleB = AngleB;
        this.AngleC = AngleC;
        this.AngleD = AngleD;
        this.BridgeA = BridgeA;
        this.FakeBlockA = FakeBlockA;
    }

    public HashMap<String, Integer> ToHashMap() {
        HashMap<String, Integer> hashmaps = new HashMap<>();
        hashmaps.put("AngleA", getAngleA());
        hashmaps.put("AngleB", getAngleB());
        hashmaps.put("AngleC", getAngleC());
        hashmaps.put("AngleD", getAngleD());
        hashmaps.put("BridgeA", getBridgeA());
        hashmaps.put("FakeBlockA", getFakeBlockA());
        return hashmaps;
    }

}
