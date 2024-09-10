package net.digii.tutorialmod.entity.custom;

import net.digii.tutorialmod.entity.ModEntities;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class BaumsoldatEntity extends HostileEntity {

    private final ServerBossBar bossBar = (ServerBossBar)new ServerBossBar(this.getDisplayName(), BossBar.Color.GREEN, BossBar.Style.PROGRESS).setDarkenSky(true);
    private static final Predicate<LivingEntity> CAN_ATTACK_PREDICATE = entity -> !entity.getType().isIn(EntityTypeTags.WITHER_FRIENDS) && entity.isMobOrPlayer();

    public final AnimationState idleAnimationState = new AnimationState();
    public int idleAnimationTimeout = 0;

    public BaumsoldatEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    private void setupAnimationStates(){
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.age);
        } else {
            this.idleAnimationTimeout--;
        }
    }

    @Override
    protected void updateLimbs(float posDelta) {
        float f = this.getPose() == EntityPose.STANDING ? Math.min(posDelta * 6.0f, 1.0f) : 0f;
        this.limbAnimator.updateLimbs(f, 0.2F);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient()){
            setupAnimationStates();
        }
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));

        //this.goalSelector.add(1, new AnimalMateGoal(this, 1.15));
        this.goalSelector.add(2, new TemptGoal(this, 1.25, Ingredient.ofItems(Items.STICK), false));
        //this.goalSelector.add(3, new MeleeAttackGoal(this, 1.0, false));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 1D));
        this.goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 4f));
        this.goalSelector.add(3, new LookAroundGoal(this));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 2, false));

        this.targetSelector.add(1, new RevengeGoal(this));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, 0, false, false, CAN_ATTACK_PREDICATE));

    }

    // Create attributes for Entity like health
    public static DefaultAttributeContainer.Builder createBaumsoldatAttributes(){
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 50)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5)
                .add(EntityAttributes.GENERIC_ARMOR, 1)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2);
    }

    /*
    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.STICK);
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.BAUMSOLDAT.create(world);
    }
     */

    //Boss stuff
    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (this.hasCustomName()) {
            this.bossBar.setName(this.getDisplayName());
        }
    }

    @Override
    public void setCustomName(@Nullable Text name) {
        super.setCustomName(name);
        this.bossBar.setName(this.getDisplayName());
    }

    @Override
    protected void mobTick() {
        this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());
    }

    public void onSummoned() {
        this.bossBar.setPercent(1.0F);
    }

    @Override
    public void onStartedTrackingBy(ServerPlayerEntity player) {
        super.onStartedTrackingBy(player);
        this.bossBar.addPlayer(player);
    }

    @Override
    public void onStoppedTrackingBy(ServerPlayerEntity player) {
        super.onStoppedTrackingBy(player);
        this.bossBar.removePlayer(player);
    }

   // @Override
   // protected @Nullable SoundEvent getHurtSound(DamageSource source) {
   //     return super.getHurtSound(source);
   // }
}
