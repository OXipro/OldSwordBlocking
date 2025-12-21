package com.oxipro.oldSwordBlocking.Listeners;

import com.oxipro.oldSwordBlocking.utils.AddBlocking;
import com.oxipro.oldSwordBlocking.utils.IsBlockingItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class PlayerInteractEvent implements Listener {

    //private final IsBlockingItem isBlockingItem;
    private final AddBlocking addBlocking;
    private final Plugin plugin;

    public PlayerInteractEvent(AddBlocking addBlocking, Plugin plugin) {
        //this.isBlockingItem = isBlockingItem;
        this.addBlocking = addBlocking;
        this.plugin = plugin;
    }

    @EventHandler
    public void onRightClick(org.bukkit.event.player.PlayerInteractEvent event) {

        if (event.getHand() != EquipmentSlot.HAND) return;
        if (!event.getAction().isRightClick()) return;
        if (event.getItem() == null) return;
        if (!event.getItem().getType().name().endsWith("_SWORD")) return;
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (IsBlockingItem.isBlockingItem(item)) return;
        addBlocking.createBlockingItem(item);
    }
}
