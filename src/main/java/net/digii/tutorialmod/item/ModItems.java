package net.digii.tutorialmod.item;

import net.digii.tutorialmod.Tutorialmod;
import net.digii.tutorialmod.item.custom.MetalDetectorItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class ModItems {

    // Items to be registered
    public static final Item[] MODITEMS = new Item[]{
            registerItem("ruby", new Item(new Item.Settings())),
            registerItem("raw_ruby", new Item(new Item.Settings())),
            registerItem("metal_detector", new MetalDetectorItem(new Item.Settings().maxDamage(64))),
            registerItem("tomato", new Item(new Item.Settings().food(ModFoodComponents.TOMATO))),
    };

    // Registers a provided item
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Tutorialmod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Tutorialmod.LOGGER.info("Registering Mod Items for " + Tutorialmod.MOD_ID);
    }
}
