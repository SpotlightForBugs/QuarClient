package com.example.module.movement;

import com.example.module.Mod;
import org.lwjgl.glfw.GLFW;

public class Jetpack extends Mod {

  public Jetpack() {
    super("Jetpack", "Allows you to fly", Category.MOVEMENT);
    this.setKey(GLFW.GLFW_KEY_J);
  }

  @Override
  public void onEnable() {
    // ModuleManager.INSTANCE.disableMod("VanillaFlight");
    // onTick();

  }

  @Override
  public void onTick() {
    if (mc.options.jumpKey.isPressed()) {
      assert mc.player != null;
      mc.player.jump();
    } else {
      // make the player fall
      assert mc.player != null;
      mc.player.setVelocity(mc.player.getVelocity().add(0, -0.1, 0));
    }
  }
}
