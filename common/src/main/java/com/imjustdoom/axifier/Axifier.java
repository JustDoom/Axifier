package com.imjustdoom.axifier;

import com.imjustdoom.axifier.config.Config;

import java.io.IOException;

public class Axifier {
    public static final String MOD_ID = "axifier";

    public static void init() {
        try {
            Config.init();
        } catch (IOException exception) {
            System.err.println("There was an error setting up or saving the config file for Axifier :(");
            exception.printStackTrace();
        }
    }
}
