package net.digii.tutorialmod.entity.client;

import net.digii.tutorialmod.Tutorialmod;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer BAUMSOLDAT =
            new EntityModelLayer(Identifier.of(Tutorialmod.MOD_ID, "baumsoldat"), "main");
}
