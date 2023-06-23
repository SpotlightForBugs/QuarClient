package com.example.module.movement;

import com.example.module.Mod;
import com.example.module.settings.*;
import org.lwjgl.glfw.GLFW;

public class Flight extends Mod {
  private boolean action = true;
  public NumberSetting speed = new NumberSetting("Speed", 5, 1, 10, 0.1);

  public Flight() {
    super("Flight", "Allows you to fly", Category.MOVEMENT);
    addSettings(speed);
    this.setKey(GLFW.GLFW_KEY_G);
  }

  @Override
  public void onTick() {

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
  }
}
