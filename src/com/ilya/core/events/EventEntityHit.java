package com.ilya.core.events;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PotionSplashEvent;

import com.ilya.core.Main;
import com.ilya.core.util.ContainerUtil;

public class EventEntityHit implements Listener {
	ContainerUtil util = new ContainerUtil();
	
	public EventEntityHit(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler	
	public void onDamage(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Player) {
			if (Main.getInstance().getConfig().getBoolean("antiIllegals.removeIllegalEnchantaments")) {
				Player d = (Player) event.getDamager();
				if (event.getDamage() > Main.getInstance().getConfig().getInt("antiIllegals.maxItemDamage")){
					util.removeIllegals(d.getInventory());
					event.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler	
	public void onThrow(PotionSplashEvent event) {	
		if (event.getPotion().getShooter() instanceof Player) {	
			if (Main.getInstance().getConfig().getBoolean("antiIllegals.potionSplashEvent")) {
				Player player = (Player) event.getPotion().getShooter();	
				if (util.hasIllegalPotionEffect(event.getPotion().getItem())) {
					util.removeIllegals(player.getInventory());
					event.setCancelled(true);	
				}
			}
		}	
	}
	
	@EventHandler
	public void onBow(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Arrow && ((Arrow) event.getDamager()).getShooter() instanceof Player) {
			if (Main.getInstance().getConfig().getBoolean("antiIllegals.bowEvent")) {
				Player d = (Player) ((Arrow) event.getDamager()).getShooter();
				if (event.getDamage() > Main.getInstance().getConfig().getInt("antiIllegals.maxBowDamage")) {
					util.removeIllegals(d.getInventory());
					event.setCancelled(true);
				}
			}
		}
	}
}
