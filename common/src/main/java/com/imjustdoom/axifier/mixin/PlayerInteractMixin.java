package com.imjustdoom.axifier.mixin;

import com.imjustdoom.axifier.config.Config;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerInteractMixin {

    @Inject(method = "interactOn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;getItemInHand(Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/item/ItemStack;", ordinal = 0), cancellable = true)
    private void interact(Entity entity, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        Player player = (Player) (Object) this;

        if (player.level().isClientSide()) return;

        ItemStack handItem = player.getItemInHand(hand);

        if (!handItem.is(ItemTags.AXES) || Config.DISABLED_MOBS.contains(entity.getType())) return;
        if (entity instanceof AgeableMob mob && !mob.isBaby()) {
            mob.setBaby(true);
            everythingElse(handItem, player, hand, entity.level(), mob);
        } else if (entity instanceof Zombie mob && !mob.isBaby()) { // Method names are the same but the Zombie does not inherit the AgeableMod class
            mob.setBaby(true);
            everythingElse(handItem, player, hand, entity.level(), mob);
        }
    }

    private void everythingElse(ItemStack handItem, Player player, InteractionHand hand, Level level, LivingEntity mob) {
        level.playSound(null, mob, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1f, 1f);
        handItem.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(hand));

        if (Math.random() >= Config.SURVIVAL_CHANCE) {
            mob.kill();
            return;
        }

        mob.hurt(level.damageSources().generic(), Config.DAMAGE);

        if (!mob.isDeadOrDying()) {
            LootTable lootTable = player.getServer().getLootData().getLootTable(mob.getLootTable());
            LootParams.Builder builder = new LootParams.Builder((ServerLevel) level)
                    .withParameter(LootContextParams.THIS_ENTITY, mob)
                    .withParameter(LootContextParams.ORIGIN, mob.position())
                    .withParameter(LootContextParams.DAMAGE_SOURCE, level.damageSources().generic())
                    .withOptionalParameter(LootContextParams.KILLER_ENTITY, player)
                    .withOptionalParameter(LootContextParams.DIRECT_KILLER_ENTITY, player);
            builder = builder.withParameter(LootContextParams.LAST_DAMAGE_PLAYER, player).withLuck(player.getLuck());
            LootParams lootParams = builder.create(LootContextParamSets.ENTITY);
            lootTable.getRandomItems(lootParams, mob.getLootTableSeed(), mob::spawnAtLocation);
        }
    }
}
