package com.ilya.core.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import com.ilya.core.Main;
import com.ilya.core.util.ContainerUtil;

public class EventBlockPlace implements Listener {
	ContainerUtil util = new ContainerUtil();
	
	public EventBlockPlace(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockPlace(BlockPlaceEvent event) {
		if (Main.getInstance().getConfig().getBoolean("antiIllegals.blockPlaceEvent")) {
			Player p = event.getPlayer();
			ItemStack m = p.getInventory().getItemInMainHand();
			ItemStack o = p.getInventory().getItemInOffHand();
			if (util.isIllegal(m)) {
				event.setCancelled(true);
				p.getInventory().remove(m);
			}
			if (util.isIllegal(o)) {
				event.setCancelled(true);
				p.getInventory().setItemInOffHand(new ItemStack(Material.AIR));
			}
		}
	}
}
