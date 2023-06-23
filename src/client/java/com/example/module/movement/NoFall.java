package com.example.module.movement;

import com.example.module.Mod;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import org.lwjgl.glfw.GLFW;

public class NoFall extends Mod {

  // The NoFall Mod is a mod that allows you to not take fall damage.

  public NoFall() {
    super("NoFall", "Allows you to not take fall damage", Category.MOVEMENT);
    this.setKey(GLFW.GLFW_KEY_H);
  }

  @Override
  public void onTick() { // gets called every tick
    ClientPlayerEntity player = mc.player;

    if (player.fallDistance <= (player.isFallFlying() ? 1 : 2)) return;

    if (player.isFallFlying() && player.isSneaking() && !(player.getVelocity().y < -0.5)) return;

    player.networkHandler.sendPacket(new PlayerMoveC2SPacket.OnGroundOnly(true));
  }
}
