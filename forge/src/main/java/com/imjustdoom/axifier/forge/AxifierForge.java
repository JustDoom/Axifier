package com.imjustdoom.axifier.forge;

import com.imjustdoom.axifier.Axifier;
import net.minecraftforge.fml.common.Mod;

@Mod(Axifier.MOD_ID)
public class AxifierForge {
    public AxifierForge() {

//        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
//        modBus.addListener((FMLCommonSetupEvent e) -> {
//        });

//        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, AxifierConfig.SPEC, "dont-run-with-scissors.toml");

        // Submit our event bus to let architectury register our content on the right time
//        EventBuses.registerModEventBus(Axifier.MOD_ID, modBus);

        Axifier.init();
    }
}
