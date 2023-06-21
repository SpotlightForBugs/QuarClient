package com.example;

import net.fabricmc.api.ClientModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

public class ExampleModClient implements ClientModInitializer {
    public static final ExampleModClient INSTANCE = new ExampleModClient();
    public Logger logger = LogManager.getLogger(ExampleModClient.class);
    @Override
    public void onInitializeClient() {
    logger.info("Hello Fabric world!");



    }

    public void onKeyPress(int key,int action) {
        if (action == GLFW.GLFW_PRESS) logger.info("Key Pressed: " + key + " Action: " + action);
    }

    public void onTick() {
        logger.info("Ticked");
    }
}