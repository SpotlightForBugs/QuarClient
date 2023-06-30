package com.example.mixin.client;

import com.example.ExampleModClient;
import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mouse.class)
public class MouseMixin {

  // left click
  @Inject(
      method = "onMouseButton",
      at = @org.spongepowered.asm.mixin.injection.At("RETURN"),
      cancellable = true)
  public void onMouseButton(long window, int button, int action, int mods, CallbackInfo ci) {
    if (button == 0 && action == 1) ExampleModClient.INSTANCE.onLeftClick();
  }
}
