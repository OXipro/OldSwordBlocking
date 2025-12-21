package com.oxipro.oldSwordBlocking.ScanInventory;

import com.oxipro.oldSwordBlocking.utils.AddBlocking;
import com.oxipro.oldSwordBlocking.utils.GetBlockingItems;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class ScanInventory extends BukkitRunnable {

    private final Plugin plugin;
    private final AddBlocking addBlocking;
    private final FileConfiguration config;

    public ScanInventory(Plugin plugin, AddBlocking addBlocking) {
        this.plugin = plugin;
        this.addBlocking = addBlocking;
        this.config = plugin.getConfig();
    }

    @Override
    public void run() {
        for (Player player : plugin.getServer().getOnlinePlayers()) {
            List<ItemStack> blockingItems = GetBlockingItems.GetBlockingItems(player);
            if (blockingItems.isEmpty()) return;
            for (ItemStack blockingItem : blockingItems) {
                addBlocking.createBlockingItem(blockingItem);
            }
        }
    }
}
