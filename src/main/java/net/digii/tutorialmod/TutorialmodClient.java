package net.digii.tutorialmod;

import net.digii.tutorialmod.block.ModBlocks;
import net.digii.tutorialmod.entity.ModEntities;
import net.digii.tutorialmod.entity.client.BaumsoldatModel;
import net.digii.tutorialmod.entity.client.BaumsoldatRenderer;
import net.digii.tutorialmod.entity.client.ModModelLayers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;

public class TutorialmodClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RUBY_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RUBY_TRAPDOOR, RenderLayer.getCutout());

        EntityRendererRegistry.register(ModEntities.BAUMSOLDAT, BaumsoldatRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BAUMSOLDAT, BaumsoldatModel::getTexturedModelData);
    }
}
