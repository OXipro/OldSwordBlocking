package com.oxipro.oldSwordBlocking.utils;

import com.oxipro.oldSwordBlocking.Main;
import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.BlocksAttacks;
import io.papermc.paper.datacomponent.item.blocksattacks.DamageReduction;
import io.papermc.paper.registry.TypedKey;
import io.papermc.paper.registry.keys.DamageTypeKeys;
import io.papermc.paper.registry.set.RegistrySet;
import net.kyori.adventure.key.Key;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.damage.DamageType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.List;

import static io.papermc.paper.registry.RegistryKey.DAMAGE_TYPE;

public class AddBlocking {

    private static FileConfiguration config;
    private static Plugin plugin;

    public AddBlocking(FileConfiguration config, Plugin plugin)  {
        this.config = config;
        this.plugin = plugin;
    }
    private static final List<TypedKey<DamageType>> BLOCKED_DAMAGE_TYPES = List.of(
            DamageTypeKeys.ARROW,
            DamageTypeKeys.FIREBALL,
            DamageTypeKeys.GENERIC,
            DamageTypeKeys.MOB_ATTACK,
            DamageTypeKeys.PLAYER_ATTACK,
            DamageTypeKeys.EXPLOSION
    );

    public ItemStack createBlockingItem(ItemStack item) {
        List<DamageReduction> reductions = BLOCKED_DAMAGE_TYPES.stream()
                .map(AddBlocking::createReduction)
                .toList();

        BlocksAttacks blocksAttacks = BlocksAttacks.blocksAttacks()
                .damageReductions(reductions)
                .blockSound(Key.key("entity.player.hurt"))
                .build();

        item.setData(DataComponentTypes.BLOCKS_ATTACKS, blocksAttacks);
        return item;
    }

    private static DamageReduction createReduction(TypedKey<DamageType> damageType) {
        String key = damageType.key().asMinimalString().toUpperCase();
        float base = (float) config.getDouble(key + ".BLOCK_BASE", 0.5);
        float factor = (float) config.getDouble(key + ".BLOCK_FACTOR", 0.0);
        return DamageReduction.damageReduction()
                .type(RegistrySet.keySet(DAMAGE_TYPE, damageType))
                .base(base)
                .factor(factor)
                .build();
    }
}
