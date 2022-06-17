package me.yailya.servercore.events;

import me.yailya.servercore.Main;
import me.yailya.servercore.util.ContainerUtil;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PotionSplashEvent;

public class EventEntityHit implements Listener {
    public EventEntityHit() {
        Main.registerListener(this);
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            if (Main.getInstance().getConfig().getBoolean("antiIllegals.removeIllegalEnchantments")) {
                Player damager = (Player) event.getDamager();

                if (damager.isOp()) return;

                if (event.getDamage() > Main.getInstance().getConfig().getInt("antiIllegals.maxItemDamage")) {
                    ContainerUtil.removeIllegals(damager.getInventory(), damager);
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onThrow(PotionSplashEvent event) {
        if (event.getPotion().getShooter() instanceof Player) {
            if (Main.getInstance().getConfig().getBoolean("antiIllegals.potionSplashEvent")) {
                Player shooter = (Player) event.getPotion().getShooter();

                if (shooter.isOp()) return;

                if (ContainerUtil.hasIllegalPotionEffect(event.getPotion().getItem())) {
                    ContainerUtil.removeIllegals(shooter.getInventory(), shooter);
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onBow(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Arrow && ((Arrow) event.getDamager()).getShooter() instanceof Player) {
            if (Main.getInstance().getConfig().getBoolean("antiIllegals.bowEvent")) {
                Player shooter = (Player) ((Arrow) event.getDamager()).getShooter();

                if (shooter.isOp()) return;

                if (event.getDamage() > Main.getInstance().getConfig().getInt("antiIllegals.maxBowDamage")) {
                    ContainerUtil.removeIllegals(shooter.getInventory(), shooter);
                    event.setCancelled(true);
                }
            }
        }
    }
}