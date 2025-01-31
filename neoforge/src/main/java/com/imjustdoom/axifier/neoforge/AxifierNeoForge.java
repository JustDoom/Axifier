package com.imjustdoom.axifier.neoforge;

import com.imjustdoom.axifier.Axifier;
import net.neoforged.fml.common.Mod;

@Mod(Axifier.MOD_ID)
public class AxifierNeoForge {
    public AxifierNeoForge() {
        Axifier.init();
    }
}
