package net.paveuu.smolbartek.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.paveuu.smolbartek.SmolBartekMod;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, SmolBartekMod.MOD_ID);

    public static final RegistryObject<MobEffect> FOR_JACOB_EFFECT = MOB_EFFECTS.register("for_jacob",
            () -> new ForJacobMobEffect(MobEffectCategory.BENEFICIAL, 0xF9B116));
    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
