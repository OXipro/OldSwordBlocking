package com.oxipro.oldSwordBlocking;

import com.oxipro.oldSwordBlocking.Listeners.EntityPickupItemEvent;
import com.oxipro.oldSwordBlocking.Listeners.PlayerInteractEvent;
import com.oxipro.oldSwordBlocking.Listeners.bw2023.onShopBuy;
import com.oxipro.oldSwordBlocking.ScanInventory.ScanInventory;
import com.oxipro.oldSwordBlocking.utils.AddBlocking;
import com.oxipro.oldSwordBlocking.utils.IsBlockingItem;
import com.tomkeuper.bedwars.api.BedWars;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {


    private AddBlocking addBlocking;
    @Override
    public void onEnable() {
        saveDefaultConfig();
        addBlocking = new AddBlocking(getConfig(), this);

        getServer().getPluginManager().registerEvents(new PlayerInteractEvent(addBlocking, this), this);
        getServer().getPluginManager().registerEvents(new EntityPickupItemEvent(addBlocking, this), this);


        if (getConfig().getBoolean("bw2023")) {
            getLogger().info("trying to load bw2023 api");
            BedWars bedwarsAPI = Bukkit.getServicesManager().getRegistration(BedWars.class).getProvider();
            getServer().getPluginManager().registerEvents(new onShopBuy(this, addBlocking), this);
        }
        getLogger().info("Loaded");
        new ScanInventory(this ,addBlocking).runTaskTimer(this, 0L, 60L);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
