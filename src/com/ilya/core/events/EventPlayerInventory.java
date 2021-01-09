package com.ilya.core.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import com.ilya.core.Main;
import com.ilya.core.util.ContainerUtil;

@SuppressWarnings("deprecation")
public class EventPlayerInventory implements Listener {
	ContainerUtil util = new ContainerUtil();
	
	public EventPlayerInventory(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onInventoryOpen(InventoryOpenEvent event) {
		if (Main.getInstance().getConfig().getBoolean("antiIllegals.inventoryOpenEvent")) {
			Player p = (Player) event.getPlayer();
			util.removeIllegals(event.getPlayer().getInventory(), p);
			util.removeIllegals(event.getInventory(), p);
		}
	}
	
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event) {
		if (Main.getInstance().getConfig().getBoolean("antiIllegals.inventoryCloseEvent")) {
			Player p = (Player) event.getPlayer();
			util.removeIllegals(event.getPlayer().getInventory(), p);
			util.removeIllegals(event.getInventory(), p);
		}
	}
	
	@EventHandler
	public void onPickup(PlayerPickupItemEvent event) {
		if (Main.getInstance().getConfig().getBoolean("antiIllegals.pickupItemEvent")) {
			ItemStack item = event.getItem().getItemStack();
			if (util.isIllegal(item)) {
				event.setCancelled(true);
				event.getItem().remove();
			}
		}
	}
}
