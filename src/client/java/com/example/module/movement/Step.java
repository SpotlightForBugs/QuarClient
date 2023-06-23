package com.example.module.movement;

import com.example.module.Mod;
import com.example.module.settings.NumberSetting;
import org.lwjgl.glfw.GLFW;

public class Step extends Mod {

  public NumberSetting height = new NumberSetting("Height", 2, 1, 10, 0.1);

  public Step() {

    super("Step", "Allows you to step up blocks", Category.MOVEMENT);
    addSetting(height);
    this.setKey(GLFW.GLFW_KEY_RIGHT);
  }

  @Override
  public void onEnable() {
    assert mc.player != null;
    mc.player.setStepHeight((float) height.getValue());
  }

  @Override
  public void onDisable() {
    assert mc.player != null;
    mc.player.setStepHeight(0.5F);
  }

  @Override
  public void onTick() {
    assert mc.player != null;
    mc.player.setStepHeight((float) height.getValue());
  }
}
