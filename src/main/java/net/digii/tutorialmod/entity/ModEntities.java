package net.digii.tutorialmod.entity;

import net.digii.tutorialmod.Tutorialmod;
import net.digii.tutorialmod.entity.custom.BaumsoldatEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static void registerModEntities() {
        Tutorialmod.LOGGER.info("Registering Entities for " + Tutorialmod.MOD_ID);
    }

    public static final EntityType<BaumsoldatEntity> BAUMSOLDAT = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Tutorialmod.MOD_ID, "baumsoldat"),
            EntityType.Builder.create(BaumsoldatEntity::new, SpawnGroup.CREATURE)
                    .dimensions(1f, 2f).build());
}
