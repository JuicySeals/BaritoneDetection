package me.juicyseals.Database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.sentry.Sentry;
import me.juicyseals.Commands.Sub.Logs;
import me.juicyseals.Interfaces.Check;
import me.juicyseals.Storage.Log;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;

public class Database {
    private HikariDataSource db;
    public Database() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306");
        config.setUsername("root");
        config.setPassword("Ss.12001947");
        db = new HikariDataSource(config);
        try {
            if (db.getConnection().isClosed()) {
                Bukkit.getLogger().log(Level.SEVERE, "Could not connect to database!");
            } else {
                Bukkit.getLogger().info("Connected to database!");
            }
        } catch (Exception e) {
            Bukkit.getLogger().info(e.getMessage());
            Sentry.captureException(e);
        }
        initDatabase();
    }

    private void initDatabase() {
        try {
            Connection conn = db.getConnection();
            conn.createStatement().execute("CREATE DATABASE IF NOT EXISTS BaritoneDetection");
            conn.createStatement().execute("USE BaritoneDetection;");
            conn.createStatement().execute("CREATE TABLE `logs` (`UUID` VARCHAR(50) NOT NULL,`AngleA` INT(50), `AngleB` INT(50), `AngleC` INT(50), `AngleD` INT(50), `BridgeA` INT(50), `FakeBlockA` INT(50))COLLATE='latin1_swedish_ci';");

        }catch (Exception e) {
            Sentry.captureException(e);
        }
    }

    public void addAlert(Check c, Player p) {
        String checkName = c.getCheckName();
        UUID uuid = p.getUniqueId();
        try {
            Connection conn = db.getConnection();
            conn.createStatement().execute("USE baritonedetection;");
            ResultSet rs = conn.createStatement().executeQuery("SELECT %s FROM logs WHERE UUID = '%s'".formatted(checkName, uuid));
            rs.next();
            int flagcount = rs.getInt(checkName)+1;
            conn.createStatement().execute("UPDATE logs SET %s = %s WHERE UUID = '%s';".formatted(checkName, flagcount, p.getUniqueId()));
        } catch (Exception e) {
            Sentry.captureException(e);
            e.printStackTrace();
        }
    }

    public void initPlayer(Player p) {
        try {
            Connection conn = db.getConnection();
            conn.createStatement().execute("INSERT INTO `baritonedetection`.`logs` (`UUID`, `AngleA`, `AngleB`, `AngleC`, `AngleD`, `BridgeA`, `FakeBlockA`) VALUES ('%s', '0', '0', '0', '0', '0', '0');".formatted(p.getUniqueId()));
        }catch (Exception e) {
            Sentry.captureException(e);
        }
    }

    public int getFlagCount(Player p, Check c) {
        String checkName = c.getCheckName();
        UUID uuid = p.getUniqueId();
        try {
            Connection conn = db.getConnection();
            conn.createStatement().execute("USE baritonedetection;");
            ResultSet rs = conn.createStatement().executeQuery("SELECT %s FROM logs WHERE UUID = '%s'".formatted(checkName, uuid));
            rs.next();
            return rs.getInt(checkName);
        } catch (Exception e) {
            Sentry.captureException(e);
            e.printStackTrace();
            return 0;
        }
    }

    public void resetFlags(Player p, Check c) {
        String checkName = c.getCheckName();
        UUID uuid = p.getUniqueId();
        try {
            Connection conn = db.getConnection();
            conn.createStatement().execute("USE baritonedetection;");
            conn.createStatement().execute("UPDATE logs SET %s = 0 WHERE UUID = '%s';".formatted(checkName, uuid));
        } catch (Exception e) {
            Sentry.captureException(e);
            e.printStackTrace();
        }
    }

    public void resetFlags(Player p) {
        UUID uuid = p.getUniqueId();
        try {
            Connection conn = db.getConnection();
            conn.createStatement().execute("USE baritonedetection;");
            conn.createStatement().execute("UPDATE logs SET AngleA = 0, AngleB = 0, AngleC = 0, AngleD = 0, BridgeA = 0, FakeBlockA = 0 WHERE UUID = '%s';".formatted(uuid));
        } catch (Exception e) {
            Sentry.captureException(e);
            e.printStackTrace();
        }
    }

    public Log getLogs(String uuid) {
        try {
            Connection conn = db.getConnection();
            conn.createStatement().execute("USE baritonedetection;");
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM logs WHERE UUID = '%s'".formatted(uuid));
            rs.next();
            return new Log(rs.getInt("AngleA"), rs.getInt("AngleB"), rs.getInt("AngleC"), rs.getInt("AngleD"), rs.getInt("BridgeA"), rs.getInt("FakeBlockA"));
        }catch (Exception e) {
            Sentry.captureException(e);
            e.printStackTrace();
            return null;
        }
    }
}
