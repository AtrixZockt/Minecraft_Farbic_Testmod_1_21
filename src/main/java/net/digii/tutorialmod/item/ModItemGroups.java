package net.digii.tutorialmod.item;

import net.digii.tutorialmod.Tutorialmod;
import net.digii.tutorialmod.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    // Custom creative mode Item group containing all modded items
    public static final ItemGroup RUBY_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Tutorialmod.MOD_ID, "ruby"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.ruby"))
                    .icon(() -> new ItemStack(ModItems.MODITEMS[0])).entries((displayContext, entries) -> {

                        // Add all items to the group
                        for (Item item : ModItems.MODITEMS)
                            entries.add(item);

                        // Add all blocks to the group
                        for (Block block : ModBlocks.MODBLOCKS)
                            entries.add(block);

                    }).build());

    public static void registerItemGroups() {
        Tutorialmod.LOGGER.info("Registering Item groups for " + Tutorialmod.MOD_ID);
    }
}
