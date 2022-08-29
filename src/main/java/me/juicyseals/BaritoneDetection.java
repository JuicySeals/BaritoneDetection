package me.juicyseals;

import io.sentry.ITransaction;
import io.sentry.Sentry;
import io.sentry.SentryEvent;
import me.juicyseals.Checks.Angle.AngleA;
import me.juicyseals.Checks.Angle.AngleB;
import me.juicyseals.Checks.Angle.AngleC;
import me.juicyseals.Checks.Angle.AngleD;
import me.juicyseals.Checks.Bridging.BridgeA;
import me.juicyseals.Checks.Misc.FakeBlockA;
import me.juicyseals.Commands.CommandHandler;
import me.juicyseals.Commands.Sub.*;
import me.juicyseals.Database.Database;
import me.juicyseals.Database.PlayerJoin;
import me.juicyseals.Storage.AlertLogs;
import me.juicyseals.Storage.FakeBlocks;
import me.juicyseals.Storage.Staff;
import me.juicyseals.Util.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;


public class BaritoneDetection extends JavaPlugin {
    public AlertLogs alertLogs = new AlertLogs(this);
    public FakeBlocks fakeBlocks = new FakeBlocks();
    public CommandHandler commandHandler = new CommandHandler(this);
    public Staff staff = new Staff();
    public Database db;
    public Alert alert = new Alert(this);
    public static String prefix;
    ITransaction transaction = Sentry.startTransaction("onEnable()", "task");
    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        Sentry.init(options -> {
            options.setDsn("https://5cba7ea6249f476ab46a61c4759d5712@o1380778.ingest.sentry.io/6694318");
            options.setTracesSampleRate(1.0);
            options.setDebug(false);
            options.setTracesSampler(
                    context -> {
                        return 1.0;
                    });
        });
        db = new Database(this);
        Bukkit.getLogger().info("Starting v" + getDescription().getVersion() + " of BaritoneDetection");
        getServer().getPluginManager().registerEvents(new AngleA(this), this);
        getServer().getPluginManager().registerEvents(new AngleB(this), this);
        getServer().getPluginManager().registerEvents(new AngleC(this), this);
        getServer().getPluginManager().registerEvents(new AngleD(this), this);
        getServer().getPluginManager().registerEvents(new BridgeA(this), this);
        getServer().getPluginManager().registerEvents(new FakeBlockA(this), this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
        getCommand("bd").setExecutor(commandHandler);
        commandHandler.registerSubCommand(new Help(this));
        commandHandler.registerSubCommand(new FakeBlockCMD(this));
        commandHandler.registerSubCommand(new Logs(this));
        commandHandler.registerSubCommand(new ResetLogs(this));
        commandHandler.registerSubCommand(new Alerts(this));
        commandHandler.registerSubCommand(new Reload(this));
        commandHandler.registerSubCommand(new Issue());
        commandHandler.registerSubCommand(new Suggest());
        prefix = ChatColor.translateAlternateColorCodes('&', getConfig().getString("prefix"));
        Metrics metrics = new Metrics(this, 16294);
    }
}
