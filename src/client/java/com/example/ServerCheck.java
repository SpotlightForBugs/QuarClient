package com.example;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

public class ServerCheck {
    public static ServerType getServerType() {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        ClientPlayerEntity player = minecraftClient.player;
        if (player != null) {
            if (player.getServer() == null) {
                return ServerType.SINGLEPLAYER;
            } else {
                return ServerType.MULTIPLAYER;
            }
        }
        return ServerType.NONE;
    }


    public enum ServerType {
        SINGLEPLAYER,
        MULTIPLAYER,
        NONE
    }
}
