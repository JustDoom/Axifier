package com.imjustdoom.axifier.forge.platform;

import com.imjustdoom.axifier.platform.services.IPlatformHelper;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class ForgePlatformHelper implements IPlatformHelper {
    @Override
    public Path getConfigPath() {
        return FMLPaths.CONFIGDIR.get();
    }
}