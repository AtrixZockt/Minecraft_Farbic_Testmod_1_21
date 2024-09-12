// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package net.digii.tutorialmod.entity.client;

import net.digii.tutorialmod.entity.animation.ModAnimations;
import net.digii.tutorialmod.entity.custom.BaumsoldatEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class BaumsoldatModel<T extends BaumsoldatEntity> extends SinglePartEntityModel<T> {
	private final ModelPart Baumsoldat;
	private final ModelPart head;

	public BaumsoldatModel(ModelPart root) {
		this.Baumsoldat = root.getChild("Baumsoldat");
		this.head = Baumsoldat.getChild("head");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData Baumsoldat = modelPartData.addChild("Baumsoldat", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData head = Baumsoldat.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F))
		.uv(32, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.25F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData body = Baumsoldat.addChild("body", ModelPartBuilder.create().uv(16, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F))
		.uv(16, 32).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData left_arm = Baumsoldat.addChild("left_arm", ModelPartBuilder.create().uv(32, 48).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
		.uv(48, 48).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(5.0F, 2.0F, 0.0F));

		ModelPartData right_arm = Baumsoldat.addChild("right_arm", ModelPartBuilder.create().uv(40, 16).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
		.uv(40, 32).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(-5.0F, 2.0F, 0.0F));

		ModelPartData left_leg = Baumsoldat.addChild("left_leg", ModelPartBuilder.create().uv(16, 48).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
		.uv(0, 48).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(1.9F, 12.0F, 0.0F));

		ModelPartData right_leg = Baumsoldat.addChild("right_leg", ModelPartBuilder.create().uv(0, 16).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
		.uv(0, 32).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(-1.9F, 12.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void setAngles(BaumsoldatEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		// Most important thing!!!
		this.getPart().traverse().forEach(ModelPart::resetTransform);

		this.setHeadAngles(netHeadYaw, headPitch);

		this.animateMovement(ModAnimations.WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.updateAnimation(entity.idleAnimationState, ModAnimations.IDLE, ageInTicks, 1f);

	}

	private void setHeadAngles(float headYaw, float headPitch){
		headYaw = MathHelper.clamp(headYaw, -30f, 30f);
		headPitch = MathHelper.clamp(headPitch, -25f, 45f);

		this.head.yaw = headYaw * 0.017453292F;
		this.head.pitch = headPitch * 0.017453292F;
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
		Baumsoldat.render(matrices, vertices, light, overlay, color);
	}

	@Override
	public ModelPart getPart() {
		return Baumsoldat;
	}
}