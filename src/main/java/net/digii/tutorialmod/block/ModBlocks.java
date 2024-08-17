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

    public static final Block RUBY_BLOCK = registerBlock("ruby_block", new Block(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));
    public static final Block RAW_RUBY_BLOCK = registerBlock("raw_ruby_block", new Block(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));
    public static final Block RUBY_ORE = registerBlock("ruby_ore", new ExperienceDroppingBlock(UniformIntProvider.create(2, 5), AbstractBlock.Settings.copy(Blocks.STONE).strength((2f))));
    public static final Block DEEPSLATE_RUBY_ORE = registerBlock("deepslate_ruby_ore", new ExperienceDroppingBlock(UniformIntProvider.create(2, 5), AbstractBlock.Settings.copy(Blocks.DEEPSLATE).strength((4f))));
    public static final Block NETHER_RUBY_ORE = registerBlock("nether_ruby_ore", new ExperienceDroppingBlock(UniformIntProvider.create(1, 3), AbstractBlock.Settings.copy(Blocks.NETHERRACK).strength((1.5f))));
    public static final Block END_STONE_RUBY_ORE = registerBlock("end_stone_ruby_ore", new ExperienceDroppingBlock(UniformIntProvider.create(3, 7), AbstractBlock.Settings.copy(Blocks.END_STONE).strength((3f))));

    public static final Block SOUND_BLOCK = registerBlock("sound_block", new SoundBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));

    // Blocks to be registered
    public static final Block[] MODBLOCKS = new Block[]{
            RUBY_BLOCK,
            RAW_RUBY_BLOCK,
            RUBY_ORE,
            DEEPSLATE_RUBY_ORE,
            NETHER_RUBY_ORE,
            END_STONE_RUBY_ORE,
            SOUND_BLOCK
    };

    // register a provided block
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Tutorialmod.MOD_ID, name), block);
    }

    // register a provided block item
    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(Tutorialmod.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        Tutorialmod.LOGGER.info("Registering blocks for: " + Tutorialmod.MOD_ID);
    }
}
