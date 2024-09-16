package net.paveuu.smolbartek.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.paveuu.smolbartek.SmolBartekMod;
import net.paveuu.smolbartek.block.ModBlocks;
import net.paveuu.smolbartek.entity.ModEntities;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SmolBartekMod.MOD_ID);

    public static final RegistryObject<Item> STRANGE_SEED = ITEMS.register("strange_seed",
            () -> new ItemNameBlockItem(ModBlocks.STRANGE_PLANT_CROP.get(),new Item.Properties()));

    public static final RegistryObject<Item> STRANGE_PLANT_EGG = ITEMS.register("strange_plant_egg",
            ()-> new ForgeSpawnEggItem(ModEntities.SMOL_BARTEK,0x7e9680,0xc5d1c5,
                    new Item.Properties()));

    public static final RegistryObject<Item> ALEBERRY = ITEMS.register("strange_plant_fruit",
            ()-> new Item(new Item.Properties().food(ModFoods.ALEBERRY)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
