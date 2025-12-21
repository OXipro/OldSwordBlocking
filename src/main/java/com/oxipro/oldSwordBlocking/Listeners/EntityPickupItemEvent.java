package com.oxipro.oldSwordBlocking.Listeners;

import com.oxipro.oldSwordBlocking.utils.AddBlocking;
import com.oxipro.oldSwordBlocking.utils.IsBlockingItem;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class EntityPickupItemEvent implements Listener {

    //private final IsBlockingItem isBlockingItem;
    private final AddBlocking addBlocking;
    private final Plugin plugin;
    private final FileConfiguration config;

    public EntityPickupItemEvent(AddBlocking addBlocking, Plugin plugin) {
        //this.isBlockingItem = isBlockingItem;
        this.addBlocking = addBlocking;
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }
    @EventHandler
    public void onEntityPickupItem(org.bukkit.event.entity.EntityPickupItemEvent e) {
        ItemStack item = e.getItem().getItemStack();
        if (IsBlockingItem.isBlockingItem(e.getItem().getItemStack())) return;
        if (item.getType().name().endsWith("_SWORD")) {
            addBlocking.createBlockingItem(item);
        }
        e.getItem().setItemStack(item);
    }

}
