package com.ilya.core.util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.block.ShulkerBox;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;

import com.ilya.core.Main;

public class ContainerUtil {
	List<Material> items = Arrays.asList( Material.BEDROCK, Material.PORTAL, Material.ENDER_PORTAL_FRAME, Material.MOB_SPAWNER, Material.COMMAND, Material.STRUCTURE_BLOCK, Material.ENDER_PORTAL, Material.BARRIER );
	boolean debug = Main.getInstance().getConfig().getBoolean("antiIllegals.debug");
	
	public boolean isIllegal(ItemStack i) {
		if (i != null) {
			return items.contains(i.getType());
		}
		return false;
	}
	
	public boolean isHasIllegalEnchantament(ItemStack itemStack) {
		Map<Enchantment, Integer> enchantaments = itemStack.getEnchantments();
		for (Enchantment en : enchantaments.keySet()) {
			int level = itemStack.getEnchantmentLevel(en);
			if (level > en.getMaxLevel()) {
				return true;
			}
		}
		return false;
	}
	
	public void removeIllegalEnchantaments(ItemStack itemStack) {
		Map<Enchantment, Integer> enchantaments = itemStack.getEnchantments();
		for (Enchantment en : enchantaments.keySet()) {
			int maxlevel = en.getMaxLevel();
			if (itemStack.getEnchantmentLevel(en) > maxlevel) {
				itemStack.removeEnchantment(en);
				itemStack.addEnchantment(en, maxlevel);
			}
		}
	}
	
	public boolean hasIllegalPotionEffect(ItemStack i) {
		if (i.getType().equals(Material.POTION)) {
			PotionMeta potion = (PotionMeta) i.getItemMeta();
			for (PotionEffect p : potion.getCustomEffects()) {
				if (p.getAmplifier() > Main.getInstance().getConfig().getInt("antiIllegals.maxPotionLevel")) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isOverStacked(ItemStack item) {
		return item.getAmount() > item.getMaxStackSize();
	}
	
	public void removeIllegals(Inventory i, Player p) {
			if (i.getContents() != null) {
				for (ItemStack item : i.getContents()) {
					if (item != null) {
						if (isHasIllegalEnchantament(item) && Main.getInstance().getConfig().getBoolean("antiIllegals.removeIllegalEnchantaments")) {
							removeIllegalEnchantaments(item);
							if (debug) {
								Main.getInstance().getLogger().info("illegal enchantments on the " + item.getType().toString() + " were found in the inventory of " + p.getName() + "'s");
							}
						}
						if (isOverStacked(item) && Main.getInstance().getConfig().getBoolean("antiIllegals.removeOverStackedItems")) {
							item.setAmount(item.getMaxStackSize());
							if (debug) {
								Main.getInstance().getLogger().info("Overstacked item - " + item.getType().toString() + ", was found in " + p.getName() + "'s inventory");
							}
						}
						if (isIllegal(item)  && Main.getInstance().getConfig().getBoolean("antiIllegals.removeIllegalItems")) {
							i.remove(item);
							if (debug) {
								Main.getInstance().getLogger().info(item.getType().toString() + " was found in the " + p.getName() + "'s inventory");
							}
						}
						if (hasIllegalPotionEffect(item) && Main.getInstance().getConfig().getBoolean("antiIllegals.removeIllegalPotions")) {
							i.remove(item);
							if (debug) {
								Main.getInstance().getLogger().info("Illegal potion found in " + p.getName() + "'s inventory");
							}
						}
						if (item.hasItemMeta()) {
							ItemMeta im = item.getItemMeta();
							if (im instanceof BlockStateMeta) {
								BlockStateMeta bsm = (BlockStateMeta) im;
								if (bsm.getBlockState() instanceof ShulkerBox) {
									ShulkerBox shulker = (ShulkerBox) bsm.getBlockState();
									for (ItemStack item2 : shulker.getInventory().getContents()) {
										if (isHasIllegalEnchantament(item2)  && Main.getInstance().getConfig().getBoolean("antiIllegals.removeIllegalEnchantaments")) {
											removeIllegalEnchantaments(item2);
											if (debug) {
												Main.getInstance().getLogger().info("illegal enchantments on the " + item.getType().toString().toLowerCase() + " were found in the inventory of " + p.getName() + "'s");
											}
										}
										if (isOverStacked(item2) && Main.getInstance().getConfig().getBoolean("antiIllegals.removeOverStackedItems")) {
											item.setAmount(item2.getMaxStackSize());
											if (debug) {
												Main.getInstance().getLogger().info("Overstacked item - " + item.getType().toString().toLowerCase() + ", was found in " + p.getName() + "'s inventory");
											}
										}
										if (isIllegal(item2) && Main.getInstance().getConfig().getBoolean("antiIllegals.removeIllegalItems")) {
											i.remove(item2);
											if (debug) {
												Main.getInstance().getLogger().info(item.getType().toString().toLowerCase() + "was found in the " + p.getName() + "'s inventory");
											}
										}
										if (hasIllegalPotionEffect(item2) && Main.getInstance().getConfig().getBoolean("antiIllegals.removeIllegalPotions")) {
											i.remove(item2);
											if (debug) {
												Main.getInstance().getLogger().info("Illegal potion found in " + p.getName() + "'s inventory");
											}
										}
									}
								}
							}
						}
					}
				}
			}
	}
}
