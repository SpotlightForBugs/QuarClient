package com.example.mixin.client;

import com.example.ExampleModClient;
import net.minecraft.client.Keyboard;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class ExampleClientKeyboardMixin {

  @Inject(method = "onKey", at = @At("HEAD"), cancellable = true)
  public void onKey(
      long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
    ExampleModClient.INSTANCE.onKeyPress(key, action);
  }

  // onLeftClick
  @Inject(method = "onKey", at = @At("HEAD"), cancellable = true)
  public void onMouseButton(
      long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
    if (key == GLFW.GLFW_MOUSE_BUTTON_LEFT && action == GLFW.GLFW_PRESS)
      ExampleModClient.INSTANCE.onLeftClick();
  }
}
