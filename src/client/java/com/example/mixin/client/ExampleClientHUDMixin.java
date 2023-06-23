package com.example.mixin.client;

import com.example.module.Mod;
import com.example.module.ModuleManager;
import java.util.Comparator;
import java.util.List;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class ExampleClientHUDMixin {

  @Shadow @Final private Random random;

  @Inject(method = "render", at = @At("HEAD"), cancellable = true)
  public void onRender(DrawContext context, float tickDelta, CallbackInfo ci) {

    if (MinecraftClient.getInstance().player == null) {
      return;
    }

    TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;

    StringBuilder allModulesString = new StringBuilder();

    List<Mod> allModules = ModuleManager.INSTANCE.getModules();
    allModules.sort(Comparator.comparing(Mod::getName));

    for (Mod mod : allModules) {
      if (mod.isEnabled()) {
        allModulesString.append(mod.getName()).append(" ");
      }
    }

    context.drawTextWithShadow(textRenderer, allModulesString.toString(), 2, 2, 0xffffff);
  }
}
