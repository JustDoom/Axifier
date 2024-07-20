package com.imjustdoom.axifier;

import com.imjustdoom.axifier.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Axifier {
    public static final String MOD_ID = "axifier";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static void init() {
        try {
            Config.init();
        } catch (IOException exception) {
            System.err.println("There was an error setting up or saving the config file for Axifier :(");
            exception.printStackTrace();
        }
    }
}
