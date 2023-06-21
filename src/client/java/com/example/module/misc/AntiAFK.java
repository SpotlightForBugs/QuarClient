package com.example.module.misc;
import com.example.module.Mod;
import org.lwjgl.glfw.GLFW;

public class AntiAFK extends Mod {

    public AntiAFK() {
        super("AntiAFK", "Prevents you from getting kicked for being AFK", Category.MISC);
        this.setKey(GLFW.GLFW_KEY_LEFT);
    }

    @Override
    public void onTick() {
        assert mc.player != null;
        mc.player.setVelocity(0, mc.player.getVelocity().getY(), 0);
        int direction = (int) (Math.random() * 8);
        switch (direction) {
            case 0 -> mc.player.setVelocity(mc.player.getVelocity().add(10, 0, 0));
            case 1 -> mc.player.setVelocity(mc.player.getVelocity().add(-10, 0, 0));
            case 2 -> mc.player.setVelocity(mc.player.getVelocity().add(0, 0, 10));
            case 3 -> mc.player.setVelocity(mc.player.getVelocity().add(0, 0, -10));
            default -> mc.player.setVelocity(mc.player.getVelocity().add(0, 0, 0)); // No movement
        }
    }





}

