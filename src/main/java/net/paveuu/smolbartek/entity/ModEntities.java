package net.paveuu.smolbartek.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.paveuu.smolbartek.SmolBartekMod;
import net.paveuu.smolbartek.entity.custom.SmolBartekEntity;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, SmolBartekMod.MOD_ID);

    public static final RegistryObject<EntityType<SmolBartekEntity>> SMOL_BARTEK =
            ENTITY_TYPES.register("shrub", ()-> EntityType.Builder.of(SmolBartekEntity::new, MobCategory.CREATURE)
                    .sized(1.5f,1.5f).build("shrub"));

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
