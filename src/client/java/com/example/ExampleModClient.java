package com.example;

import com.example.module.Mod;
import com.mojang.authlib.exceptions.MinecraftClientException;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;
import com.example.module.ModuleManager;

public class ExampleModClient implements ClientModInitializer {
    public static final ExampleModClient INSTANCE = new ExampleModClient();
    public ServerCheck serverCheck = new ServerCheck();
    public static ClientPlayerEntity player = MinecraftClient.getInstance().player;


    public Logger logger = LogManager.getLogger(ExampleModClient.class);
    private MinecraftClient mc = MinecraftClient.getInstance();
    @Override
    public void onInitializeClient() {
        logger.info("Welcome to " + getWindowTitle());


    }





    public void onKeyPress(int key, int action) {
        if (mc.player != null && mc.world != null) {
            if ( mc.currentScreen == null || (!(mc.currentScreen instanceof ChatScreen))) {
                if (action == GLFW.GLFW_PRESS) {
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
        }
    }


    public void onTick() {

        if (mc.player != null && mc.world != null) {
            for (Mod mod : ModuleManager.INSTANCE.getEnabledModules()) {
                mod.onTick();
            }
        }
    }

    public String getWindowTitle() {
        return "QuarClient by SFB";
    }
}