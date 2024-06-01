package com.imjustdoom.axifier.fabric;

import com.imjustdoom.axifier.Axifier;
import net.fabricmc.api.ModInitializer;

public class AxifierFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Axifier.init();
    }
}
