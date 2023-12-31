package com.imjustdoom.axifier.forge;

import com.imjustdoom.axifier.Axifier;
import com.imjustdoom.axifier.forge.configuration.AxifierConfig;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Axifier.MOD_ID)
public class AxifierForge {
    public AxifierForge() {

        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener((FMLCommonSetupEvent e) -> {
        });

//        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, AxifierConfig.SPEC, "dont-run-with-scissors.toml");

        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(Axifier.MOD_ID, modBus);

        Axifier.init();
    }
}
