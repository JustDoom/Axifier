package com.imjustdoom.axifier.fabric;

import com.imjustdoom.axifier.Axifier;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class AxifierFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Axifier.init();
    }
}
