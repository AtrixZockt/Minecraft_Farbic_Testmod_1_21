package net.digii.tutorialmod.block;

import net.digii.tutorialmod.Tutorialmod;
import net.digii.tutorialmod.block.custom.SoundBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModBlocks {

    // Blocks to be registered
    public static final Block[] MODBLOCKS = new Block[]{
            registerBlock("ruby_block", new Block(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK))),
            registerBlock("raw_ruby_block", new Block(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK))),
            registerBlock("ruby_ore", new ExperienceDroppingBlock(UniformIntProvider.create(2, 5), AbstractBlock.Settings.copy(Blocks.STONE).strength((2f)))),
            registerBlock("deepslate_ruby_ore", new ExperienceDroppingBlock(UniformIntProvider.create(2, 5), AbstractBlock.Settings.copy(Blocks.DEEPSLATE).strength((4f)))),
            registerBlock("nether_ruby_ore", new ExperienceDroppingBlock(UniformIntProvider.create(1, 3), AbstractBlock.Settings.copy(Blocks.NETHERRACK).strength((1.5f)))),
            registerBlock("end_stone_ruby_ore", new ExperienceDroppingBlock(UniformIntProvider.create(3, 7), AbstractBlock.Settings.copy(Blocks.END_STONE).strength((3f)))),

            registerBlock("sound_block", new SoundBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK))),
    };

    // register a provided block
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Tutorialmod.MOD_ID, name), block);
    }

    // register a provided block item
    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, Identifier.of(Tutorialmod.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        Tutorialmod.LOGGER.info("Registering blocks for: " + Tutorialmod.MOD_ID);
    }
}
