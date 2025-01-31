package com.imjustdoom.axifier.neoforge.platform;

import com.imjustdoom.axifier.platform.services.IPlatformHelper;
import net.neoforged.fml.loading.FMLPaths;

import java.nio.file.Path;

public class NeoForgePlatformHelper implements IPlatformHelper {
    @Override
    public Path getConfigPath() {
        return FMLPaths.CONFIGDIR.get();
    }
}