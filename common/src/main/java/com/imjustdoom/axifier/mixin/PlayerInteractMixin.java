package com.imjustdoom.axifier.mixin;

import com.imjustdoom.axifier.config.Config;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
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

import java.util.Optional;

@Mixin(Player.class)
public abstract class PlayerInteractMixin {

    @Inject(method = "interactOn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;getItemInHand(Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/item/ItemStack;", ordinal = 0), cancellable = true)
    private void interact(Entity entity, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        Player player = (Player) (Object) this;

        if (player.level().isClientSide() || !(entity.level() instanceof ServerLevel level)) {
            return;
        }
        ItemStack handItem = player.getItemInHand(hand);

        if (!handItem.is(ItemTags.AXES) || Config.DISABLED_MOBS.contains(entity.getType())) return;
        if (entity instanceof AgeableMob mob && !mob.isBaby()) {
            mob.setBaby(true);
            everythingElse(handItem, player, hand, mob, level);
        } else if (entity instanceof Zombie mob && !mob.isBaby()) {
            mob.setBaby(true);
            everythingElse(handItem, player, hand, mob, level);
        }
    }

    private void everythingElse(ItemStack handItem, Player player, InteractionHand hand, LivingEntity mob, ServerLevel level) {
        level.playSound(null, mob, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1f, 1f);
        handItem.hurtAndBreak(1, player, hand == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);

        if (Math.random() >= Config.SURVIVAL_CHANCE) {
            mob.kill(level);
            return;
        }

        mob.hurtServer(level, level.damageSources().generic(), Config.DAMAGE);

        if (!mob.isDeadOrDying()) {
            Optional<ResourceKey<LootTable>> lootTableResourceKey = mob.getLootTable();
            if (lootTableResourceKey.isEmpty()) {
                return;
            }
            LootTable lootTable = player.getServer().reloadableRegistries().getLootTable(lootTableResourceKey.get());
            LootParams.Builder builder = new LootParams.Builder(level)
                    .withParameter(LootContextParams.THIS_ENTITY, mob)
                    .withParameter(LootContextParams.ORIGIN, mob.position())
                    .withParameter(LootContextParams.DAMAGE_SOURCE, level.damageSources().generic())
                    .withOptionalParameter(LootContextParams.ATTACKING_ENTITY, player)
                    .withOptionalParameter(LootContextParams.DIRECT_ATTACKING_ENTITY, player);
            builder = builder.withParameter(LootContextParams.LAST_DAMAGE_PLAYER, player).withLuck(player.getLuck());
            LootParams lootParams = builder.create(LootContextParamSets.ENTITY);
            lootTable.getRandomItems(lootParams, mob.getLootTableSeed(), (item) -> mob.spawnAtLocation(level, item));
        }
    }
}
