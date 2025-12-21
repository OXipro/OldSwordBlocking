package com.oxipro.oldSwordBlocking.Listeners.bw2023;

import com.oxipro.oldSwordBlocking.utils.AddBlocking;
import com.oxipro.oldSwordBlocking.utils.GetBlockingItems;
import com.tomkeuper.bedwars.api.events.shop.ShopBuyEvent;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class onShopBuy implements Listener {
    private final Plugin plugin;
    private final AddBlocking addBlocking;
    private final FileConfiguration config;

    public onShopBuy(Plugin plugin, AddBlocking addBlocking) {
        this.plugin = plugin;
        this.addBlocking = addBlocking;
        this.config = plugin.getConfig();
    }

    @EventHandler
    public void onShopBuyEvent(ShopBuyEvent e) {
        if (!e.getCategoryContent().getCategoryIdentifier().endsWith("sword")) return;
        List<ItemStack> blockingItems = GetBlockingItems.GetBlockingItems(e.getBuyer());
        if (blockingItems.isEmpty()) return;
        for (ItemStack blockingItem : blockingItems) {
            addBlocking.createBlockingItem(blockingItem);
        }
    }
}
