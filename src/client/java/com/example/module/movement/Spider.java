package com.example.module.movement;

import com.example.module.Mod;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;

public class Spider extends Mod {

  public Spider() {
    super("Spider", "Allows you to climb up walls", Category.MOVEMENT);
    this.setKey(GLFW.GLFW_KEY_UP);
  }

  @Override
  public void onTick() {
    ClientPlayerEntity player = mc.player;
    assert player != null;
    if (!player.horizontalCollision) return;

    Vec3d velocity = player.getVelocity();
    if (velocity.y >= 0.2) return;

    player.setVelocity(velocity.x, 0.2, velocity.z);

  }
}
