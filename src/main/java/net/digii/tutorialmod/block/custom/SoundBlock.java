package net.digii.tutorialmod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class SoundBlock extends Block {

    public SoundBlock(Settings settings) {
        super(settings);
    }

    // being right-clicked by player
    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {

        // play a sound
        world.playSound(player, pos, SoundEvents.BLOCK_NOTE_BLOCK_XYLOPHONE.value(), SoundCategory.BLOCKS, 1f, 1f);

        return ActionResult.SUCCESS;
    }

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        return super.onBreak(world, pos, state, player);
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
        tooltip.add(Text.translatable("tooltip.tutorialmod.sound_block.tooltip").formatted(Formatting.AQUA));
        super.appendTooltip(stack, context, tooltip, options);
    }
}
