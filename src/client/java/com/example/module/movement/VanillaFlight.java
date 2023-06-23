package com.example.module.movement;

import com.example.module.Mod;
import org.lwjgl.glfw.GLFW;

public class VanillaFlight extends Mod {

  public VanillaFlight() {
    super("VanillaFlight", "Allows you to fly", Category.MOVEMENT);
    this.setKey(GLFW.GLFW_KEY_G);
  }

  @Override
  public void onTick() {
    assert mc.player != null;
    mc.player.getAbilities().flying = true;
  }

  @Override
  public void onDisable() {
    assert mc.player != null;
    mc.player.getAbilities().flying = false;
  }

  @Override
  public void onEnable() {
    assert mc.player != null;
    // ModuleManager.INSTANCE.disableMod("Jetpack");
  }
}
