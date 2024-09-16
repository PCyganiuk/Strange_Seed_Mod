package net.paveuu.smolbartek.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.event.ForgeEventFactory;
import net.paveuu.smolbartek.entity.ModEntities;
import net.paveuu.smolbartek.item.ModItems;
import org.jetbrains.annotations.Nullable;

public class SmolBartekEntity extends TamableAnimal {


    public SmolBartekEntity(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState sittingAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    private int ageTicks = 0;
    private static final EntityDataAccessor<Integer> FRUIT_COUNT = SynchedEntityData.defineId(SmolBartekEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> POT_COLOR = SynchedEntityData.defineId(SmolBartekEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> HAS_POT = SynchedEntityData.defineId(SmolBartekEntity.class, EntityDataSerializers.BOOLEAN);


    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide()){
            setupAnimationStates();
        }

        if(this.entityData.get(FRUIT_COUNT) < 9){
            final int GROWTH_TIME = 18000;
            this.ageTicks++;
            if(this.ageTicks >= GROWTH_TIME){
                int currentCount = this.entityData.get(FRUIT_COUNT);
                setFruitCount(currentCount + 1);
                this.ageTicks = 0;
            }
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(FRUIT_COUNT,0);
        this.entityData.define(POT_COLOR, 0xE57757);
        this.entityData.define(HAS_POT,false);
    }
    public int getPotColor() {
        return this.entityData.get(POT_COLOR);
    }

    public boolean getHasPot(){
        return this.entityData.get(HAS_POT);
    }

    public void setHasPot(){
        this.entityData.set(HAS_POT,true);
    }

    public void setPotColor(int color) {
        this.entityData.set(POT_COLOR, color);
    }

    public int getFruitCount() {
        return this.entityData.get(FRUIT_COUNT);
    }

    public void setFruitCount(int count) {
        this.entityData.set(FRUIT_COUNT, count);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("FruitCount", this.getFruitCount());

        compound.putInt("PotColor", this.getPotColor());

        compound.putBoolean("HasPot", this.entityData.get(HAS_POT));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setFruitCount(compound.getInt("FruitCount"));
        this.setPotColor(compound.getInt("PotColor"));
        this.entityData.set(HAS_POT, compound.getBoolean("HasPot"));
    }

    private void setupAnimationStates(){
        if(this.idleAnimationTimeout <= 0){
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        }
        else{
            --this.idleAnimationTimeout;
        }

        if (this.isInSittingPose()) {
            this.idleAnimationState.stop();
            this.idleAnimationTimeout = 0;
            this.sittingAnimationState.start(this.tickCount);
        } else {
            this.sittingAnimationState.stop();
        }
    }

    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        if(this.getPose() == Pose.STANDING){
            f = Math.min(pPartialTick * 6f, 1f);
        }
        else{
            f = 0f;
        }
        this.walkAnimation.update(f, 0.2f);
    }


    @Override
    protected void registerGoals(){
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(3, new FollowOwnerGoal(this, 1.0, 10.0F, 2.0F, false));

        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.1D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0f));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes(){
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 20D)
                .add(Attributes.FOLLOW_RANGE, 24D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D) //too slow movement
                .add(Attributes.ARMOR_TOUGHNESS, 0.1f)
                .add(Attributes.ATTACK_KNOCKBACK, 0.5f)
                .add(Attributes.ATTACK_DAMAGE, 2f);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob ageableMob) {
        return ModEntities.SMOL_BARTEK.get().create(pLevel);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.DOLPHIN_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.DOLPHIN_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.DOLPHIN_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pPos, BlockState pState) {
        this.playSound(SoundEvents.WOLF_STEP, 0.15F, 1.0F);
    }

    @Override
    protected void onOffspringSpawnedFromEgg(Player pPlayer, Mob pChild) {
        super.onOffspringSpawnedFromEgg(pPlayer, pChild);
        this.setTame(true);
        this.setOwnerUUID(pPlayer.getUUID());
    }

    @Override
    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        Item item = itemstack.getItem();
        if (this.level().isClientSide) {
            boolean flag = this.isOwnedBy(pPlayer) || this.isTame() || itemstack.is(Items.WHEAT_SEEDS) && !this.isTame();
            return flag ? InteractionResult.CONSUME : InteractionResult.PASS;
        }
        else if (this.isTame()) {
            if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
                this.heal((float)itemstack.getFoodProperties(this).getNutrition());
                if (!pPlayer.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }

                this.gameEvent(GameEvent.EAT, this);
                return InteractionResult.SUCCESS;
            }
            else if (item instanceof DyeItem dyeItem) {
                DyeColor dyeColor = dyeItem.getDyeColor();
                int color = dyeColor.getTextColor();
                this.setPotColor(color);
                if (!pPlayer.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }

                return InteractionResult.SUCCESS;
            }
            else if (itemstack.is(Items.SHEARS) && this.entityData.get(FRUIT_COUNT) > 0){
                ItemStack fruits = new ItemStack(ModItems.ALEBERRY.get(),this.entityData.get(FRUIT_COUNT)+1);
                Level level = this.level();
                double x = this.getX();
                double y  = this.getY();
                double z = this.getZ();
                ItemEntity itemEntity = new ItemEntity(level,x,y,z,fruits);
                level.addFreshEntity(itemEntity);
                level.playSound(null, x, y, z, SoundEvents.SHEEP_SHEAR, SoundSource.PLAYERS, 1.0F, 1.0F);

                if (!pPlayer.getAbilities().instabuild) {
                    itemstack.hurtAndBreak(this.entityData.get(FRUIT_COUNT), pPlayer, (player) -> player.broadcastBreakEvent(pHand));
                }
                setFruitCount(0);
                return InteractionResult.SUCCESS;
            }
            else {
                InteractionResult interactionresult = super.mobInteract(pPlayer, pHand);
                if ((!interactionresult.consumesAction() || this.isBaby()) && this.isOwnedBy(pPlayer)) {
                    this.setOrderedToSit(!this.isOrderedToSit());
                    this.jumping = false;
                    this.navigation.stop();
                    this.setTarget((LivingEntity)null);
                    return InteractionResult.SUCCESS;
                } else {
                    return interactionresult;
                }
            }
        }
        else if (itemstack.is(Items.FLOWER_POT)) {
            if (!pPlayer.getAbilities().instabuild) {
                itemstack.shrink(1);
            }

            if (!ForgeEventFactory.onAnimalTame(this, pPlayer)) {
                this.tame(pPlayer);
                setHasPot();
                this.navigation.stop();
                this.setTarget((LivingEntity)null);
                this.setOrderedToSit(true);
                this.level().broadcastEntityEvent(this, (byte)7);
            }
            else {
                this.level().broadcastEntityEvent(this, (byte)6);
            }

            return InteractionResult.SUCCESS;
        }
        else {
            return super.mobInteract(pPlayer, pHand);
        }
    }
}
