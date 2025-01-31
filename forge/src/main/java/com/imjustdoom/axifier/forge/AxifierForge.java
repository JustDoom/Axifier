package com.imjustdoom.axifier.forge;

import com.imjustdoom.axifier.Axifier;
import net.minecraftforge.fml.common.Mod;

@Mod(Axifier.MOD_ID)
public class AxifierForge {

    public AxifierForge() {
        Axifier.init();
    }
}