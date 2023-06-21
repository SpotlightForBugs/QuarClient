package com.example.module.movement;

import com.example.module.Mod;
import org.lwjgl.glfw.GLFW;

public class Step extends Mod {
    public Step() {
        super("Step", "Allows you to step up n blocks", Category.MOVEMENT);
        this.setKey(GLFW.GLFW_KEY_RIGHT);
    }

    @Override
    public void onEnable() {
        assert mc.player != null;
        mc.player.setStepHeight(2.0F);
    }

    @Override
    public void onDisable()
    {
        assert mc.player != null;
        mc.player.setStepHeight(0.5F);
    }

    @Override
    public void onTick() {
        assert mc.player != null;
        mc.player.setStepHeight(2.0F);
    }






}
