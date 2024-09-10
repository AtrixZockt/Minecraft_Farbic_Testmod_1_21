package net.digii.tutorialmod.datagen;

import net.digii.tutorialmod.block.ModBlocks;
import net.digii.tutorialmod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.Models;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

        BlockStateModelGenerator.BlockTexturePool rubyPool = null;
        for (Block block : ModBlocks.MODBLOCKS)
        {
            if (block == ModBlocks.RUBY_BLOCK){
                rubyPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.RUBY_BLOCK);
                continue;
            }

            blockStateModelGenerator.registerSimpleCubeAll(block);
        }

        if (rubyPool == null)
            return;

        rubyPool.stairs(ModBlocks.RUBY_STAIRS);
        rubyPool.slab(ModBlocks.RUBY_SLAB);
        rubyPool.button(ModBlocks.RUBY_BUTTON);
        rubyPool.pressurePlate(ModBlocks.RUBY_PRESSURE_PLATE);
        rubyPool.fence(ModBlocks.RUBY_FENCE);
        rubyPool.fenceGate(ModBlocks.RUBY_FENCE_GATE);
        rubyPool.wall(ModBlocks.RUBY_WALL);

        blockStateModelGenerator.registerDoor(ModBlocks.RUBY_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.RUBY_TRAPDOOR);
    }

    public void generateRubyPoolBlockStateModels()
    {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        for (Item item : ModItems.MODITEMS)
        {
            if (item == ModItems.BAUMSOLDAT_SPAWN_EGG)
            {
                itemModelGenerator.register(ModItems.BAUMSOLDAT_SPAWN_EGG,
                        new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));
                continue;
            }

            itemModelGenerator.register(item, Models.GENERATED);
        }

    }
}
