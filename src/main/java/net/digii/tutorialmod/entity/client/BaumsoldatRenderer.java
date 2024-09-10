package net.digii.tutorialmod.entity.client;

import net.digii.tutorialmod.Tutorialmod;
import net.digii.tutorialmod.entity.custom.BaumsoldatEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class BaumsoldatRenderer extends MobEntityRenderer<BaumsoldatEntity, BaumsoldatModel<BaumsoldatEntity>> {
    private static final Identifier TEXTURE = Identifier.of(Tutorialmod.MOD_ID, "textures/entity/baumsoldat.png");

    public BaumsoldatRenderer(EntityRendererFactory.Context context) {
        super(context, new BaumsoldatModel<>(context.getPart(ModModelLayers.BAUMSOLDAT)), 0.6f);
    }

    @Override
    public Identifier getTexture(BaumsoldatEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(BaumsoldatEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {

        if(livingEntity.isBaby()){
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(1f, 1f, 1f);
        }

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
