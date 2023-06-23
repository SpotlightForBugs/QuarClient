package com.example.ui.screens;

import com.example.module.Mod;
import com.example.module.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;
import java.util.Comparator;
import java.util.List;

public class HUD {

    private static MinecraftClient mc = MinecraftClient.getInstance();
    public static void render(DrawContext context, float delta) {
        renderArrayList(context, delta);

    }

    public static void renderArrayList(DrawContext context, float delta) {



        int index = 0;
        int sWidth = mc.getWindow().getScaledWidth();

        List<Mod> enabled = ModuleManager.INSTANCE.getEnabledModules();
        enabled.sort(Comparator.comparingInt(m -> (int) mc.textRenderer.getWidth(((Mod)m).getDisplayName())).reversed());

        for (Mod mod : ModuleManager.INSTANCE.getEnabledModules()) {
            if (mod.isEnabled()) {
                context.drawTextWithShadow(mc.textRenderer, mod.getDisplayName(), (sWidth - 4) - mc.textRenderer.getWidth(mod.getDisplayName()), 15 + (index * mc.textRenderer.fontHeight), Color.RED.getRGB());
                index++;
            }
        }
    }

}
