package me.yailya.servercore.util;

import me.yailya.servercore.Main;
import org.bukkit.Material;
import org.bukkit.block.ShulkerBox;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ContainerUtil {
    private static final FileConfiguration config = Main.getInstance().getConfig();
    private static final List<Material> items = Arrays.asList(
            Material.BEDROCK,
            Material.PORTAL,
            Material.ENDER_PORTAL_FRAME,
            Material.MOB_SPAWNER,
            Material.COMMAND,
            Material.STRUCTURE_BLOCK,
            Material.ENDER_PORTAL,
            Material.BARRIER
    );
    private static final boolean debug = config.getBoolean("antiIllegals.debug");

    public static boolean isIllegal(ItemStack stack) {
        if (stack != null) {
            return items.contains(stack.getType());
        }

        return false;
    }

    public static boolean hasIllegalEnchantments(ItemStack stack) {
        Map<Enchantment, Integer> enchantmentMap = stack.getEnchantments();
        for (Enchantment en : enchantmentMap.keySet()) {
            int level = stack.getEnchantmentLevel(en);
            if (level > en.getMaxLevel()) {
                return true;
            }
        }

        return false;
    }

    public static void removeIllegalEnchantments(ItemStack stack) {
        Map<Enchantment, Integer> enchantmentMap = stack.getEnchantments();
        for (Enchantment en : enchantmentMap.keySet()) {
            int maxLevel = en.getMaxLevel();
            if (stack.getEnchantmentLevel(en) > maxLevel) {
                stack.removeEnchantment(en);
                stack.addEnchantment(en, maxLevel);
            }
        }
    }

    public static boolean hasIllegalPotionEffect(ItemStack stack) {
        if (stack.getType().equals(Material.POTION)) {
            PotionMeta potion = (PotionMeta) stack.getItemMeta();
            for (PotionEffect p : potion.getCustomEffects()) {
                if (p.getAmplifier() > config.getInt("antiIllegals.maxPotionLevel")) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean isOverStacked(ItemStack item) {
        return item.getAmount() > item.getMaxStackSize();
    }

    public static void removeIllegals(Inventory inventory, Player p) {
        if (inventory.getContents() != null) {
            for (ItemStack item : inventory.getContents()) {
                if (item != null) {
                    if (hasIllegalEnchantments(item) && config.getBoolean("antiIllegals.removeIllegalEnchantments")) {
                        removeIllegalEnchantments(item);
                        info("Illegal enchantments on the " + item.getType().toString() + " were found in the inventory of " + p.getName() + "'s");
                    }

                    if (isOverStacked(item) && config.getBoolean("antiIllegals.removeOverStackedItems")) {
                        item.setAmount(item.getMaxStackSize());
                        info("Overstacked item - " + item.getType().toString() + ", was found in " + p.getName() + "'s inventory");
                    }

                    if (isIllegal(item) && config.getBoolean("antiIllegals.removeIllegalItems")) {
                        inventory.remove(item);
                        info(item.getType().toString() + " was found in the " + p.getName() + "'s inventory");
                    }

                    if (hasIllegalPotionEffect(item) && config.getBoolean("antiIllegals.removeIllegalPotions")) {
                        inventory.remove(item);
                        info("Illegal potion found in " + p.getName() + "'s inventory");
                    }

                    if (item.hasItemMeta()) {
                        ItemMeta im = item.getItemMeta();

                        if (im instanceof BlockStateMeta) {
                            BlockStateMeta bsm = (BlockStateMeta) im;

                            if (bsm.getBlockState() instanceof ShulkerBox) {
                                ShulkerBox shulkerBox = (ShulkerBox) bsm.getBlockState();

                                for (ItemStack item2 : shulkerBox.getInventory().getContents()) {
                                    if (hasIllegalEnchantments(item2) && Main.getInstance().getConfig().getBoolean("antiIllegals.removeIllegalEnchantments")) {
                                        removeIllegalEnchantments(item2);
                                        info("Illegal enchantments on the " + item.getType().toString().toLowerCase() + " were found in the inventory of " + p.getName() + "'s");
                                    }
                                    if (isOverStacked(item2) && config.getBoolean("antiIllegals.removeOverStackedItems")) {
                                        item.setAmount(item2.getMaxStackSize());
                                        info("Overstacked item - " + item.getType().toString().toLowerCase() + ", was found in " + p.getName() + "'s inventory");
                                    }
                                    if (isIllegal(item2) && config.getBoolean("antiIllegals.removeIllegalItems")) {
                                        inventory.remove(item2);
                                        info(item.getType().toString().toLowerCase() + "was found in the " + p.getName() + "'s inventory");
                                    }
                                    if (hasIllegalPotionEffect(item2) && config.getBoolean("antiIllegals.removeIllegalPotions")) {
                                        inventory.remove(item2);
                                        info("Illegal potion found in " + p.getName() + "'s inventory");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static void info(String message) {
        if (debug) {
            Main.getInstance().getLogger().info(message);
        }
    }
}