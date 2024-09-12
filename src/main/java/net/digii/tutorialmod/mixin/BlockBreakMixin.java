package net.digii.tutorialmod.mixin;

import net.digii.tutorialmod.Tutorialmod;
import net.digii.tutorialmod.entity.ModEntities;
import net.digii.tutorialmod.entity.custom.BaumsoldatEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public class BlockBreakMixin {
	@Inject(at = @At("HEAD"), method = "onBreak")
	private void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player, CallbackInfoReturnable<BlockState> cir) {
		if (!world.isClient())
		{
			// Check if borken block is a log (@TODO Check if it's a tree)
			// Check if enough time has passed since last Boss spawn. 1sec = 20ticks
			if (state.isIn(BlockTags.LOGS) && (world.getTime() - Tutorialmod.lastBaumsoldatBossSpawnTime) >= Tutorialmod.BAUMSOLDAT_BOSS_TIME_BETWEEN_SPAWNS)
			{
				boolean isTree = false;

				int blocksToCheck = 2;
				int airCount = 0;
				int posX = pos.getX(), posY = pos.getY(), posZ = pos.getZ();

				BlockState block;
				BlockPos blockPosAboveBlock;

				// Check up to 16 blocks above current block if block is leave or a log (if there are 2 air blocks in a row, exit)
				for (int i = 0; i <= blocksToCheck; i++) {
					if (airCount >= 2)
						break;

					blockPosAboveBlock = new BlockPos(posX, posY + i, posZ);
					block = world.getBlockState(blockPosAboveBlock);

					if (block.isIn(BlockTags.LEAVES))
					{
						isTree = true;
						break;
					}

					if (block.isIn(BlockTags.LOGS))
					{
						if (i == blocksToCheck)
							blocksToCheck++;

						continue;
					}

					if (block.isIn(BlockTags.AIR))
						airCount++;
				}

				// We suspect that current block is part of a tree
				if (isTree)
				{
					player.sendMessage(Text.literal("This is a tree"), false);

					Random randomNumGenerator = Random.create();
					int randomNumber = randomNumGenerator.nextInt(100);

					if (randomNumber <= Tutorialmod.BAUMSOLDAT_BOSS_SPAWN_CHANCE)
					{
						BaumsoldatEntity entity = ModEntities.BAUMSOLDAT.create(world);
						assert entity != null;
						entity.updatePosition(pos.getX(), pos.getY(), pos.getZ());
						entity.refreshPositionAndAngles(pos, 0f, 0f);
						world.spawnEntity(entity);

						Tutorialmod.lastBaumsoldatBossSpawnTime = world.getTime();
					}
				}
			}
		}
	}
}
