package net.paveuu.smolbartek.entity.client;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import net.paveuu.smolbartek.entity.animations.ModAnimationDefinitions;
import net.paveuu.smolbartek.entity.custom.SmolBartekEntity;

public class SmolBartekModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart shrub;
	private final ModelPart pot;
	private SmolBartekEntity smolBartekEntity;

	public SmolBartekModel(ModelPart root) {
		this.shrub = root.getChild("shrub");
		this.pot = this.shrub.getChild("pot");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition shrub = partdefinition.addOrReplaceChild("shrub", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition left_leg = shrub.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(30, 23).addBox(2.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_leg = shrub.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(30, 28).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 0.0F, 0.0F));

		PartDefinition left_arm = shrub.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.offset(10.0F, -11.0F, 0.0F));

		PartDefinition left_r1 = left_arm.addOrReplaceChild("left_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition right_arm = shrub.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.offset(-10.0F, -11.0F, 0.0F));

		PartDefinition right_r1 = right_arm.addOrReplaceChild("right_r1", CubeListBuilder.create().texOffs(0, 23).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition pot = shrub.addOrReplaceChild("pot", CubeListBuilder.create().texOffs(36, 0).addBox(-3.0F, -19.75F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(0, 43).addBox(-2.0F, -22.75F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 23.0F, 0.0F)); //y offset=-1.25

		PartDefinition torso = shrub.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(39, 31).addBox(1.0F, -7.0F, -6.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(43, 35).addBox(2.0F, -8.0F, -5.0F, 10.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(43, 35).addBox(2.0F, 5.0F, -5.0F, 10.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(45, 36).addBox(3.0F, -9.0F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(44, 36).addBox(3.0F, 6.0F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, -10.0F, 0.0F));

		PartDefinition leaves_front = torso.addOrReplaceChild("leaves_front", CubeListBuilder.create(), PartPose.offset(7.0F, 10.0F, 0.0F));

		PartDefinition cube_r1 = leaves_front.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -1.0F, -5.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r2 = leaves_front.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -2.0F, -6.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r3 = leaves_front.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -11.0F, -6.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r4 = leaves_front.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, -5.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r5 = leaves_front.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -7.0F, -5.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r6 = leaves_front.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -7.0F, -7.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r7 = leaves_front.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, -16.0F, -6.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r8 = leaves_front.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, -18.0F, -5.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r9 = leaves_front.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -14.0F, -7.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition leaves_left = torso.addOrReplaceChild("leaves_left", CubeListBuilder.create(), PartPose.offsetAndRotation(7.0F, 10.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r10 = leaves_left.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -1.0F, -4.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r11 = leaves_left.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -8.0F, -7.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r12 = leaves_left.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -2.0F, -6.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r13 = leaves_left.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -2.0F, -5.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r14 = leaves_left.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -6.0F, -5.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r15 = leaves_left.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -11.0F, -5.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r16 = leaves_left.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, -5.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r17 = leaves_left.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -7.0F, -6.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r18 = leaves_left.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -8.0F, -7.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r19 = leaves_left.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, -16.0F, -6.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r20 = leaves_left.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -13.0F, -7.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r21 = leaves_left.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, -11.0F, -8.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r22 = leaves_left.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, -18.0F, -4.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r23 = leaves_left.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -14.0F, -7.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition leaves_back = torso.addOrReplaceChild("leaves_back", CubeListBuilder.create(), PartPose.offsetAndRotation(7.0F, 10.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r24 = leaves_back.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -1.0F, -4.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r25 = leaves_back.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -8.0F, -7.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r26 = leaves_back.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -2.0F, -6.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r27 = leaves_back.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -2.0F, -5.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r28 = leaves_back.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -6.0F, -5.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r29 = leaves_back.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -11.0F, -5.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r30 = leaves_back.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, -5.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r31 = leaves_back.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -7.0F, -6.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r32 = leaves_back.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -8.0F, -7.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r33 = leaves_back.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, -16.0F, -6.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r34 = leaves_back.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -13.0F, -7.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r35 = leaves_back.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, -11.0F, -8.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r36 = leaves_back.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, -18.0F, -4.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r37 = leaves_back.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -14.0F, -7.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition leaves_right = torso.addOrReplaceChild("leaves_right", CubeListBuilder.create(), PartPose.offsetAndRotation(7.0F, 10.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r38 = leaves_right.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -1.0F, -4.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r39 = leaves_right.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -8.0F, -7.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r40 = leaves_right.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -2.0F, -6.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r41 = leaves_right.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -2.0F, -5.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r42 = leaves_right.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -6.0F, -5.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r43 = leaves_right.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -11.0F, -5.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r44 = leaves_right.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, -5.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r45 = leaves_right.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -7.0F, -6.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r46 = leaves_right.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -8.0F, -7.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r47 = leaves_right.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, -16.0F, -6.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r48 = leaves_right.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -13.0F, -7.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r49 = leaves_right.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, -11.0F, -8.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r50 = leaves_right.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, -18.0F, -4.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r51 = leaves_right.addOrReplaceChild("cube_r51", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -14.0F, -7.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition leaves_top = torso.addOrReplaceChild("leaves_top", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.0F, -1.0F, 0.0F, 0.0F, 1.5708F, 1.5708F));

		PartDefinition cube_r52 = leaves_top.addOrReplaceChild("cube_r52", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -1.0F, -4.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r53 = leaves_top.addOrReplaceChild("cube_r53", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -8.0F, -7.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r54 = leaves_top.addOrReplaceChild("cube_r54", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -2.0F, -6.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r55 = leaves_top.addOrReplaceChild("cube_r55", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -2.0F, -5.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r56 = leaves_top.addOrReplaceChild("cube_r56", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -6.0F, -5.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r57 = leaves_top.addOrReplaceChild("cube_r57", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -11.0F, -5.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r58 = leaves_top.addOrReplaceChild("cube_r58", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, -5.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r59 = leaves_top.addOrReplaceChild("cube_r59", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -7.0F, -6.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r60 = leaves_top.addOrReplaceChild("cube_r60", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -8.0F, -7.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r61 = leaves_top.addOrReplaceChild("cube_r61", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, -16.0F, -6.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r62 = leaves_top.addOrReplaceChild("cube_r62", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -13.0F, -7.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r63 = leaves_top.addOrReplaceChild("cube_r63", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, -11.0F, -8.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r64 = leaves_top.addOrReplaceChild("cube_r64", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, -18.0F, -4.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r65 = leaves_top.addOrReplaceChild("cube_r65", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -14.0F, -7.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition leaves_bottom = torso.addOrReplaceChild("leaves_bottom", CubeListBuilder.create(), PartPose.offsetAndRotation(18.0F, -1.0F, 0.0F, 0.0F, 1.5708F, -1.5708F));

		PartDefinition cube_r66 = leaves_bottom.addOrReplaceChild("cube_r66", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -1.0F, -4.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r67 = leaves_bottom.addOrReplaceChild("cube_r67", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -8.0F, -7.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r68 = leaves_bottom.addOrReplaceChild("cube_r68", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -2.0F, -6.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r69 = leaves_bottom.addOrReplaceChild("cube_r69", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -2.0F, -5.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r70 = leaves_bottom.addOrReplaceChild("cube_r70", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -6.0F, -5.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r71 = leaves_bottom.addOrReplaceChild("cube_r71", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -11.0F, -5.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r72 = leaves_bottom.addOrReplaceChild("cube_r72", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, -5.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r73 = leaves_bottom.addOrReplaceChild("cube_r73", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -7.0F, -6.0F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r74 = leaves_bottom.addOrReplaceChild("cube_r74", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -8.0F, -7.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r75 = leaves_bottom.addOrReplaceChild("cube_r75", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, -16.0F, -6.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r76 = leaves_bottom.addOrReplaceChild("cube_r76", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -13.0F, -7.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r77 = leaves_bottom.addOrReplaceChild("cube_r77", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, -11.0F, -8.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r78 = leaves_bottom.addOrReplaceChild("cube_r78", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, -18.0F, -4.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r79 = leaves_bottom.addOrReplaceChild("cube_r79", CubeListBuilder.create().texOffs(79, 31).addBox(2.0F, 0.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -14.0F, -7.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition fruits = torso.addOrReplaceChild("fruits", CubeListBuilder.create(), PartPose.offset(7.0F, 10.0F, 0.0F));

		PartDefinition fruit0 = fruits.addOrReplaceChild("fruit0", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -9.0F, -13.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(1, 55).addBox(-6.0F, -8.0F, -14.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 6.0F));

		PartDefinition fruit1 = fruits.addOrReplaceChild("fruit1", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -9.0F, -13.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(1, 55).addBox(-6.0F, -8.0F, -14.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 0.0F, 5.0F));

		PartDefinition fruit2 = fruits.addOrReplaceChild("fruit2", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -9.0F, -13.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(1, 55).addBox(-6.0F, -8.0F, -14.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(10.0F, 0.0F, 6.0F));

		PartDefinition fruit3 = fruits.addOrReplaceChild("fruit3", CubeListBuilder.create().texOffs(0, 1).addBox(-6.0F, -9.0F, -13.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(1, 55).addBox(-6.0F, -8.0F, -14.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(12.0F, 0.0F, 10.0F));

		PartDefinition fruit4 = fruits.addOrReplaceChild("fruit4", CubeListBuilder.create().texOffs(1, 1).addBox(-5.0F, -9.0F, -13.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(1, 55).addBox(-6.0F, -8.0F, -14.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 0.0F, 11.0F));

		PartDefinition fruit5 = fruits.addOrReplaceChild("fruit5", CubeListBuilder.create().texOffs(0, 1).addBox(-6.0F, -9.0F, -13.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(1, 55).addBox(-6.0F, -8.0F, -14.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(11.0F, 0.0F, 16.0F));

		PartDefinition fruit6 = fruits.addOrReplaceChild("fruit6", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -9.0F, -14.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(1, 55).addBox(-6.0F, -8.0F, -14.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, 0.0F, 19.0F));

		PartDefinition fruit7 = fruits.addOrReplaceChild("fruit7", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -9.0F, -14.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(1, 55).addBox(-6.0F, -8.0F, -14.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 0.0F, 19.0F));

		PartDefinition fruit8 = fruits.addOrReplaceChild("fruit8", CubeListBuilder.create().texOffs(1, 1).addBox(-5.0F, -9.0F, -13.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(1, 55).addBox(-6.0F, -8.0F, -14.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 0.0F, 16.0F));

		PartDefinition eyes = torso.addOrReplaceChild("eyes", CubeListBuilder.create().texOffs(56, 61).addBox(-4.0F, -1.5F, -0.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(56, 61).addBox(1.0F, -1.5F, -0.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, -1.5F, -6.5F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		if(entity instanceof SmolBartekEntity){
			this.smolBartekEntity = (SmolBartekEntity) entity;
		}
		this.animateWalk(ModAnimationDefinitions.WALKING,limbSwing,limbSwingAmount,3f,1f);
		this.animate(((SmolBartekEntity) entity).idleAnimationState, ModAnimationDefinitions.IDLE,ageInTicks,1f);
		this.animate(((SmolBartekEntity) entity).sittingAnimationState, ModAnimationDefinitions.SITTING,ageInTicks,1f);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		ModelPart torso = shrub.getChild("torso");
		ModelPart fruits = torso.getChild("fruits");
		int potColor = this.smolBartekEntity.getPotColor();
		float potRed = ((potColor >> 16) & 0xFF) / 255.0F;
		float potGreen = ((potColor >> 8) & 0xFF) / 255.0F;
		float potBlue = (potColor & 0xFF) / 255.0F;
		int fruitCount = smolBartekEntity.getFruitCount();
		fruits.getChild("fruit0").visible = fruitCount >= 1;
		fruits.getChild("fruit5").visible = fruitCount >= 2;
		fruits.getChild("fruit7").visible = fruitCount >= 3;
		fruits.getChild("fruit3").visible = fruitCount >= 4;
		fruits.getChild("fruit6").visible = fruitCount >= 5;
		fruits.getChild("fruit1").visible = fruitCount >= 6;
		fruits.getChild("fruit4").visible = fruitCount >= 7;
		fruits.getChild("fruit2").visible = fruitCount >= 8;
		fruits.getChild("fruit8").visible = fruitCount >= 9;
		this.shrub.getChild("pot").visible = false;
		shrub.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		if(smolBartekEntity.getHasPot()) {
			this.shrub.getChild("pot").visible = true;
			pot.render(poseStack, vertexConsumer, packedLight, packedOverlay, potRed, potGreen, potBlue, alpha);
		}
	}

	@Override
	public ModelPart root() {
		return shrub;
	}
}