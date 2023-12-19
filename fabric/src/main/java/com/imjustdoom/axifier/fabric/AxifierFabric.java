package com.imjustdoom.axifier.fabric;

import com.imjustdoom.axifier.Axifier;
import com.imjustdoom.axifier.block.BlockInit;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;

public class AxifierFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Axifier.init();

        FlammableBlockRegistry flammableBlockRegistry = FlammableBlockRegistry.getDefaultInstance();
        flammableBlockRegistry.add(BlockInit.SUGAR_CANE_BLOCK.get(), 5, 20);
        flammableBlockRegistry.add(BlockInit.SUGAR_CANE_SLAB.get(), 5, 20);
        flammableBlockRegistry.add(BlockInit.SUGAR_CANE_STAIRS.get(), 5, 20);

        flammableBlockRegistry.add(BlockInit.WHITE_WOOL_STAIRS.get(), 30, 60);
        flammableBlockRegistry.add(BlockInit.ORANGE_WOOL_STAIRS.get(), 30, 60);
        flammableBlockRegistry.add(BlockInit.MAGENTA_WOOL_STAIRS.get(), 30, 60);
        flammableBlockRegistry.add(BlockInit.LIGHT_BLUE_WOOL_STAIRS.get(), 30, 60);
        flammableBlockRegistry.add(BlockInit.YELLOW_WOOL_STAIRS.get(), 30, 60);
        flammableBlockRegistry.add(BlockInit.LIME_WOOL_STAIRS.get(), 30, 60);
        flammableBlockRegistry.add(BlockInit.PINK_WOOL_STAIRS.get(), 30, 60);
        flammableBlockRegistry.add(BlockInit.GRAY_WOOL_STAIRS.get(), 30, 60);
        flammableBlockRegistry.add(BlockInit.LIGHT_GRAY_WOOL_STAIRS.get(), 30, 60);
        flammableBlockRegistry.add(BlockInit.CYAN_WOOL_STAIRS.get(), 30, 60);
        flammableBlockRegistry.add(BlockInit.PURPLE_WOOL_STAIRS.get(), 30, 60);
        flammableBlockRegistry.add(BlockInit.BLUE_WOOL_STAIRS.get(), 30, 60);
        flammableBlockRegistry.add(BlockInit.BROWN_WOOL_STAIRS.get(), 30, 60);
        flammableBlockRegistry.add(BlockInit.GREEN_WOOL_STAIRS.get(), 30, 60);
        flammableBlockRegistry.add(BlockInit.RED_SAND_STAIRS.get(), 30, 60);
        flammableBlockRegistry.add(BlockInit.BLACK_WOOL_STAIRS.get(), 30, 60);

        flammableBlockRegistry.add(BlockInit.WHITE_WOOL_SLAB.get(), 30, 60);
        flammableBlockRegistry.add(BlockInit.ORANGE_WOOL_SLAB.get(), 30, 60);
        flammableBlockRegistry.add(BlockInit.MAGENTA_WOOL_SLAB.get(), 30, 60);
        flammableBlockRegistry.add(BlockInit.LIGHT_BLUE_WOOL_SLAB.get(), 30, 60);
        flammableBlockRegistry.add(BlockInit.YELLOW_WOOL_SLAB.get(), 30, 60);
        flammableBlockRegistry.add(BlockInit.LIME_WOOL_SLAB.get(), 30, 60);
        flammableBlockRegistry.add(BlockInit.PINK_WOOL_SLAB.get(), 30, 60);
        flammableBlockRegistry.add(BlockInit.GRAY_WOOL_SLAB.get(), 30, 60);
        flammableBlockRegistry.add(BlockInit.LIGHT_GRAY_WOOL_SLAB.get(), 30, 60);
        flammableBlockRegistry.add(BlockInit.CYAN_WOOL_SLAB.get(), 30, 60);
        flammableBlockRegistry.add(BlockInit.PURPLE_WOOL_SLAB.get(), 30, 60);
        flammableBlockRegistry.add(BlockInit.BLUE_WOOL_SLAB.get(), 30, 60);
        flammableBlockRegistry.add(BlockInit.BROWN_WOOL_SLAB.get(), 30, 60);
        flammableBlockRegistry.add(BlockInit.GREEN_WOOL_SLAB.get(), 30, 60);
        flammableBlockRegistry.add(BlockInit.RED_SAND_SLAB.get(), 30, 60);
        flammableBlockRegistry.add(BlockInit.BLACK_WOOL_SLAB.get(), 30, 60);
    }
}
