package net.digii.tutorialmod.util;

import net.digii.tutorialmod.Tutorialmod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {

        // custom block tag to be registered
        public static final TagKey<Block> METAL_DETECTOR_DETECTABLE_BLOCKS =
                createTag("metal_detector_detectable_blocks");

        // register a custom tag for blocks
        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(Tutorialmod.MOD_ID, name));
        }
    }

    public static class Items {

        // register a custom tag for items
        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(Tutorialmod.MOD_ID, name));
        }
    }
}
