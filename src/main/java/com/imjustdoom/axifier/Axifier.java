package com.imjustdoom.axifier;

import com.imjustdoom.axifier.config.Config;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Mod(Axifier.MOD_ID)
public class Axifier {

    public static final String MOD_ID = "axifier";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public Axifier() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        try {
            Config.init();
        } catch (IOException exception) {
            LOGGER.error("There was an error setting up or saving the config file for Axifier :(");
            exception.printStackTrace();
        }
    }
}
