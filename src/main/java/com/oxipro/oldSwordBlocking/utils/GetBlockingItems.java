package com.oxipro.oldSwordBlocking.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class GetBlockingItems {
    public static List<ItemStack> GetBlockingItems(Player p) {
        List<ItemStack> BlockingItems = new ArrayList<>();

        for (ItemStack item : p.getInventory().getContents()) {
            if (item == null) continue;
            if (IsBlockingItem.isBlockingItem(item)) continue;
            if (item.getType().name().endsWith("_SWORD")) {
                BlockingItems.add(item);
            }
        }
        return BlockingItems;
    }
}
