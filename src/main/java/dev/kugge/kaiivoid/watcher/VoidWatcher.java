package dev.kugge.kaiivoid.watcher;

import dev.kugge.kaiivoid.Kaiivoid;
import dev.kugge.kaiivoid.util.MerchantSnapshot;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Merchant;

public class VoidWatcher implements Listener {
    @EventHandler
    public void onInventoryOpen(final InventoryOpenEvent event) {
        if (!(event.getInventory().getHolder() instanceof Villager)) return;
        Kaiivoid.snapshots.put(
                (Player) event.getPlayer(),
                new MerchantSnapshot((Merchant) event.getInventory().getHolder())
        );
    }

    @EventHandler
    public void onInventoryClose(final InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        if (!Kaiivoid.snapshots.containsKey(player)) return;

        MerchantSnapshot snapshot = Kaiivoid.snapshots.get(player);
        if (shouldReset(snapshot.villager)) snapshot.resetMerchant();

        Kaiivoid.snapshots.remove(player);
    }

    private static boolean shouldReset(Villager villager) {
        double distance = villager.getWorld().getSimulationDistance();
        // Simulate chunk loaded by a player
        for (Entity entity : villager.getNearbyEntities(distance, distance, distance))
            if (entity instanceof Player) return false;
        return true;
    }
}
