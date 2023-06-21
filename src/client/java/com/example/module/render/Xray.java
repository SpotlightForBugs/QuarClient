package com.example.module.render;

import com.example.module.Mod;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import org.lwjgl.glfw.GLFW;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

public class Xray extends Mod {

    public Xray() {
        super("Xray", "Allows you to see ores through walls", Category.RENDER);
        this.setKey(GLFW.GLFW_KEY_X);
    }

  @Override
    public void onEnable() {
       load_xray_resource_pack();
       mc.worldRenderer.reload();
    }

    private void load_xray_resource_pack() {

        //send message to chat (Only visible to you)
        Objects.requireNonNull(mc.player).sendMessage(Text.of("Not implemented yet!"), false);


    }
}
