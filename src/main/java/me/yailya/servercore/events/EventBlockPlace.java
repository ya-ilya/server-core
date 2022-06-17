package me.yailya.servercore.events;

import me.yailya.servercore.Main;
import me.yailya.servercore.util.ContainerUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class EventBlockPlace implements Listener {
    public EventBlockPlace() {
        Main.registerListener(this);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockPlace(BlockPlaceEvent event) {
        if (Main.getInstance().getConfig().getBoolean("antiIllegals.blockPlaceEvent")) {
            Player player = event.getPlayer();

            if (player.isOp()) return;

            ItemStack mainHand = player.getInventory().getItemInMainHand();
            ItemStack offHand = player.getInventory().getItemInOffHand();

            if (ContainerUtil.isIllegal(mainHand)) {
                event.setCancelled(true);
                player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
            }

            if (ContainerUtil.isIllegal(offHand)) {
                event.setCancelled(true);
                player.getInventory().setItemInOffHand(new ItemStack(Material.AIR));
            }
        }
    }
}