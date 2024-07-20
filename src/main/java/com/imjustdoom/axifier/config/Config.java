package com.imjustdoom.axifier.config;

import com.imjustdoom.axifier.Axifier;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Config {

    private static Path FILE_PATH;
    private static Properties PROPERTIES;

    public static float DAMAGE;
    public static float SURVIVAL_CHANCE;
    public static boolean AFFECTS_ZOMBIES;
    public static List<EntityType<?>> DISABLED_MOBS;

    public static void init() throws IOException {
        PROPERTIES = new Properties();
        FILE_PATH = Path.of(FMLPaths.CONFIGDIR.get() + "/axifier.properties");
        if (!FILE_PATH.toFile().exists()) {
            new File(FILE_PATH.toString()).createNewFile();
        }
        PROPERTIES.load(new FileInputStream(FILE_PATH.toFile()));

        DAMAGE = getFloat("damage", "2.0");
        SURVIVAL_CHANCE = getFloat("survival-chance", "1.0");
        AFFECTS_ZOMBIES = getBoolean("affects-zombies", "true");
        DISABLED_MOBS = getEntityTypeList("disabled-mobs", "minecraft:villager,minecraft:wandering_trader");

        save();
    }

    private static String getString(final String setting, final String defaultValue) {
        String value = PROPERTIES.getProperty(setting);
        if (value == null) {
            PROPERTIES.setProperty(setting, defaultValue);
            value = defaultValue;
        }
        return value;
    }

    private static int getInt(final String setting, final String defaultValue) {
        String value = PROPERTIES.getProperty(setting);
        if (value == null) {
            PROPERTIES.setProperty(setting, defaultValue);
            value = defaultValue;
        }
        return Integer.parseInt(value);
    }

    private static float getFloat(final String setting, final String defaultValue) {
        String value = PROPERTIES.getProperty(setting);
        if (value == null) {
            PROPERTIES.setProperty(setting, defaultValue);
            value = defaultValue;
        }
        return Float.parseFloat(value);
    }

    private static boolean getBoolean(final String setting, final String defaultValue) {
        String value = PROPERTIES.getProperty(setting);
        if (value == null) {
            PROPERTIES.setProperty(setting, defaultValue);
            value = defaultValue;
        }
        return Boolean.parseBoolean(value);
    }

    private static List<String> getStringList(final String setting, final String defaultValue) {
        String value = PROPERTIES.getProperty(setting);
        if (value == null) {
            PROPERTIES.setProperty(setting, defaultValue);
            value = defaultValue;
        }
        return Arrays.asList(value.split(","));
    }

    private static List<EntityType<?>> getEntityTypeList(final String setting, final String defaultValue) {
        List<String> stringList = getStringList(setting, defaultValue);
        List<EntityType<?>> entityTypeList = new ArrayList<>();
        for (String entity : stringList) {
            if (entity.isBlank()) continue;
            EntityType.byString(entity).ifPresentOrElse((entityType) -> {
                entityTypeList.add(entityType);
                Axifier.LOGGER.info("EntityType \"{}\" has been added to the disabled list", entityType.getDescriptionId());
            }, () -> {
                Axifier.LOGGER.warn("EntityType \"{}\" was unable to be found", entity);
            });
        }
        return entityTypeList;
    }

    public static void save() throws IOException {
        PROPERTIES.store(new FileWriter(FILE_PATH.toFile()),
                """
                        Config for Axifier
                        'damage' is how much damage the entity will take when "stripped". Default 2.0
                        'survival-chance' the chance for the entity to survive being "stripped",
                        it is on a scale of 0-1. 0 is 0%, 1 is 100% and 0.32 is 32%. Default 1.0
                        'affects-zombies' sets if axifier should work on zombies. Default true
                        'disabled-mobs' is a list of entities that will not be affected by this axe
                                                example 'disabled-mobs=minecraft:villager,minecraft:cow,tabs:chickenman'
                        """);
    }
}