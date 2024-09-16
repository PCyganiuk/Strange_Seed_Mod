package net.paveuu.smolbartek.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class ForJacobMobEffect extends MobEffect {

    protected ForJacobMobEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        pLivingEntity.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 6000, pAmplifier, false, false));
        pLivingEntity.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 6000, pAmplifier, false, false));
        pLivingEntity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 400, pAmplifier, false, false));
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return pDuration % 20 == 0;
    }

}
