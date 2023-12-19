package com.imjustdoom.axifier;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.InteractionEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

public class Axifier {
    public static final String MOD_ID = "axifier";

    public static void init() {

        InteractionEvent.INTERACT_ENTITY.register((player, entity, hand) -> {

            if (player.getServer() == null) return EventResult.pass();
            if (entity instanceof AgeableMob mob && !mob.isBaby() && player.getItemInHand(hand).is(ItemTags.AXES)) {
                Level level = mob.level();
                level.playSound(null, entity, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1f, 1f);

                mob.setBaby(true);
                mob.hurt(level.damageSources().generic(), 2f);

                player.getItemInHand(hand).hurtAndBreak(1, player, p -> p.broadcastBreakEvent(hand));

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

//                return EventResult.interruptDefault();
            }
            return EventResult.pass();
        });
    }
}
