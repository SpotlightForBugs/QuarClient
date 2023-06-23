package com.example.module.movement;

import com.example.module.Mod;

public class Sprint extends Mod {

  public Sprint() {
    super("Sprint", "Automatically sprints", Category.MOVEMENT);
  }

  @Override
  public void onTick() {
    assert mc.player != null;
    mc.player.setSprinting(true);
  }

  @Override
    public void onDisable() {
    assert mc.player != null;
    mc.player.setSprinting(false);
  }
}
