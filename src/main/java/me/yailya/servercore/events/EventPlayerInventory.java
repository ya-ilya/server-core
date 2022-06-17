package me.yailya.servercore.events;

import me.yailya.servercore.Main;
import me.yailya.servercore.util.ContainerUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

@SuppressWarnings("deprecation")
public class EventPlayerInventory implements Listener {
    public EventPlayerInventory() {
        Main.registerListener(this);
    }

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        if (Main.getInstance().getConfig().getBoolean("antiIllegals.inventoryOpenEvent")) {
            Player player = (Player) event.getPlayer();

            if (player.isOp()) return;

            ContainerUtil.removeIllegals(event.getPlayer().getInventory(), player);
            ContainerUtil.removeIllegals(event.getInventory(), player);
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (Main.getInstance().getConfig().getBoolean("antiIllegals.inventoryCloseEvent")) {
            Player player = (Player) event.getPlayer();

            if (player.isOp()) return;

            ContainerUtil.removeIllegals(event.getPlayer().getInventory(), player);
            ContainerUtil.removeIllegals(event.getInventory(), player);
        }
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent event) {
        if (event.getPlayer().isOp()) return;

        if (Main.getInstance().getConfig().getBoolean("antiIllegals.pickupItemEvent")) {
            if (ContainerUtil.isIllegal(event.getItem().getItemStack())) {
                event.setCancelled(true);
                event.getItem().remove();
            }
        }
    }
}