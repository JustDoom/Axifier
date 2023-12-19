package com.imjustdoom.axifier.forge;

import com.imjustdoom.axifier.Axifier;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Axifier.MOD_ID)
public class AxifierForge {
    public AxifierForge() {

        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener((FMLCommonSetupEvent e) -> {
        });

        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(Axifier.MOD_ID, modBus);

        Axifier.init();
    }
}
