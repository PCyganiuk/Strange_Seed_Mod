package net.paveuu.smolbartek.event;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.paveuu.smolbartek.SmolBartekMod;
import net.paveuu.smolbartek.entity.ModEntities;
import net.paveuu.smolbartek.entity.custom.SmolBartekEntity;

@Mod.EventBusSubscriber(modid = SmolBartekMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
        event.put(ModEntities.SMOL_BARTEK.get(), SmolBartekEntity.createAttributes().build());
    }
}
