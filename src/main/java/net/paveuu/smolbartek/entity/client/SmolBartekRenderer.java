package net.paveuu.smolbartek.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.paveuu.smolbartek.SmolBartekMod;
import net.paveuu.smolbartek.entity.custom.SmolBartekEntity;

public class SmolBartekRenderer extends MobRenderer<SmolBartekEntity, SmolBartekModel<SmolBartekEntity>> {
    public SmolBartekRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new SmolBartekModel<>(pContext.bakeLayer(ModModelLayers.SMOL_BARTEK_LAYER)),1f);
    }

    @Override
    public ResourceLocation getTextureLocation(SmolBartekEntity smolBartekEntity) {
        return new ResourceLocation(SmolBartekMod.MOD_ID,"textures/entity/bush_texture.png");
    }

    @Override
    public void render(SmolBartekEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight){
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
