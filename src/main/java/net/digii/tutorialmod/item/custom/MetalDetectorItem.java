package net.digii.tutorialmod.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

import java.util.Objects;

public class MetalDetectorItem extends Item {

    // Constructor class
    public MetalDetectorItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (!context.getWorld().isClient())
        {
            BlockPos positionClicked = context.getBlockPos();
            PlayerEntity player = context.getPlayer();
            boolean foundBlock = false;

            assert player != null;

            if (Objects.equals(player.getName().getString(), "AtrixZockt"))
            {
                player.sendMessage(Text.literal("Hi atrix"), true);
            }

            for (int i = 0; i < positionClicked.getY() + 64; i++) {
                BlockState state = context.getWorld().getBlockState(positionClicked.down(i));

                if (isValuableBlock(state))
                {
                    outputValuableCoordinates(positionClicked.down(i), player, state.getBlock());
                    foundBlock = true;

                    break;
                }
            }

            if (!foundBlock)
            {
                player.sendMessage(Text.literal("No Valuables Found!"), false);
            }
        }

        context.getStack().damage(1, Objects.requireNonNull(context.getPlayer()), EquipmentSlot.MAINHAND);

        return ActionResult.SUCCESS;
    }


    private void outputValuableCoordinates(BlockPos blockPos, PlayerEntity player, Block block) {
        player.sendMessage(Text.literal("Found " + block.asItem().getName().getString() + " at " +
                "(" + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + ")"), false);
    }


    private boolean isValuableBlock(BlockState state) {
        return state.isOf(Blocks.DIAMOND_ORE) || state.isOf(Blocks.IRON_ORE);
    }
}
