package dev.kugge.kaiivoid;

import dev.kugge.kaiivoid.util.MerchantSave;
import dev.kugge.kaiivoid.watcher.VoidWatcher;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class Kaiivoid extends JavaPlugin {

    public static Kaiivoid instance;
    public static Logger logger;
    public static Map<Player, MerchantSave> snapshots = new ConcurrentHashMap<>();

    @Override
    public void onEnable() {
        instance = this;
        logger = getLogger();
        register();
    }

    private void register(){
        Bukkit.getPluginManager().registerEvents(new VoidWatcher(), this);
    }
}
