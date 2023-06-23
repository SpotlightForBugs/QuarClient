package com.example.module.render;

import com.example.module.Mod;
import java.util.Objects;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class Xray extends Mod {

  public Xray() {
    super("Xray", "Allows you to see ores through walls", Category.RENDER);
    this.setKey(GLFW.GLFW_KEY_X);
  }

  @Override
  public void onEnable() {
    load_xray_resource_pack();
    mc.worldRenderer.reload();
  }

  private void load_xray_resource_pack() {

    // send message to chat (Only visible to you)
    Objects.requireNonNull(mc.player).sendMessage(Text.of("Not implemented yet!"), false);
  }
}
