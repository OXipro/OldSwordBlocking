package com.oxipro.oldSwordBlocking.utils;

import io.papermc.paper.datacomponent.DataComponentTypes;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class IsBlockingItem {
    public static boolean isBlockingItem(@NotNull ItemStack item) {
        return item != null
                && !item.isEmpty()
                && item.hasData(DataComponentTypes.BLOCKS_ATTACKS);
    }
}
