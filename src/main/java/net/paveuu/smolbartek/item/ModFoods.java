package net.paveuu.smolbartek.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.paveuu.smolbartek.effect.ModEffects;

public class ModFoods {
    public static final FoodProperties ALEBERRY = new FoodProperties.Builder()
            .nutrition(2)
            .saturationMod(0.4f)
            .effect(()-> new MobEffectInstance(ModEffects.FOR_JACOB_EFFECT.get(),200),1f).build();
}
