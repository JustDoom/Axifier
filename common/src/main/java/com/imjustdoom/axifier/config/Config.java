package com.imjustdoom.axifier.config;

import dev.architectury.injectables.annotations.ExpectPlatform;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;

public class Config {

    private static Path FILE_PATH;
    private static Properties PROPERTIES;

    public static float DAMAGE;
    public static float SURVIVAL_CHANCE;

    public static void init() throws IOException {
        PROPERTIES = new Properties();
        FILE_PATH = Path.of(getConfigDirectory() + "/axifier.properties");
        if (!FILE_PATH.toFile().exists()) {
            new File(FILE_PATH.toString()).createNewFile();
        }
        PROPERTIES.load(new FileInputStream(FILE_PATH.toFile()));

        DAMAGE = getFloat("damage", "2.0");
        SURVIVAL_CHANCE = getFloat("survival-chance", "1.0");

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

    public static void save() throws IOException {
        PROPERTIES.store(new FileWriter(FILE_PATH.toFile()),
                """
                        Config for Axifier
                        'damage' is how much damage the entity will take when "stripped". Default 2.0
                        'survival-chance' the chance for the entity to survive being "stripped",
                        it is on a scale of 0-1. 0 is 0%, 1 is 100% and 0.32 is 32%. Default 1.0
                        'affects-zombies' sets if axifier should work on zombies. Default true
                        """);
    }

    @ExpectPlatform
    public static Path getConfigDirectory() {
        throw new AssertionError();
    }
}