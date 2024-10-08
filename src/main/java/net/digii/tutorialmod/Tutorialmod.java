package net.digii.tutorialmod;

import net.digii.tutorialmod.block.ModBlocks;
import net.digii.tutorialmod.entity.ModEntities;
import net.digii.tutorialmod.entity.custom.BaumsoldatEntity;
import net.digii.tutorialmod.item.ModItemGroups;
import net.digii.tutorialmod.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tutorialmod implements ModInitializer {

	// This is the mod id saved as a static to use instead of writing mod id string every time
	public static final String MOD_ID = "tutorialmod";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	// Baumsoldat Boss variables
	public static final Integer BAUMSOLDAT_BOSS_SPAWN_CHANCE = 10; //Chance in %
	public static final Integer BAUMSOLDAT_BOSS_TIME_BETWEEN_SPAWNS = 6000; //6000ticks = 5min

	public static Long lastBaumsoldatBossSpawnTime = 0L;

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();

		ModBlocks.registerModBlocks();

		ModEntities.registerModEntities();

		// Simple way to add fuel item (TODO: change used item once Item array is removed)
		FuelRegistry.INSTANCE.add(ModItems.COAL_BRIQUETTE, 200);

		FabricDefaultAttributeRegistry.register(ModEntities.BAUMSOLDAT, BaumsoldatEntity.createBaumsoldatAttributes());
	}
}