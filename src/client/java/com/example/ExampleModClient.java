package com.example;

import com.example.module.Mod;
import com.mojang.authlib.exceptions.MinecraftClientException;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;
import com.example.module.ModuleManager;

public class ExampleModClient implements ClientModInitializer {
    public static final ExampleModClient INSTANCE = new ExampleModClient();
    public Logger logger = LogManager.getLogger(ExampleModClient.class);
    private MinecraftClient mc = MinecraftClient.getInstance();
    @Override
    public void onInitializeClient() {
    logger.info("Hello Fabric world!");



    }

    public void onKeyPress(int key,int action) {
        if (action == GLFW.GLFW_PRESS){
            for (Mod mod : ModuleManager.INSTANCE.getModules()) {
                if (mod.getKey() == key) {
                    if (mod.isEnabled()) {
                       logger.info("Disabling " + mod.getName());

                    } else {
                        logger.info("Enabling " + mod.getName());
                    }
                    mod.toggle();
                }
            }


        }
    }

    public void onTick() {
        if (mc.player != null) {
            for (Mod mod : ModuleManager.INSTANCE.getEnabledModules()) {
                mod.onTick();
            }
        }
    }
}