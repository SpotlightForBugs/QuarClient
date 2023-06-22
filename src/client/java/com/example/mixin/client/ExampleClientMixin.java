package com.example.mixin.client;

import com.example.ExampleModClient;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class ExampleClientMixin {
	@Inject(method = "tick", at = @At("HEAD"), cancellable = true)
		public void onTick(CallbackInfo ci) {
			ExampleModClient.INSTANCE.onTick();
		}






		@Inject(method = "tick", at = @At("RETURN"), cancellable = true)
	public void changeWindowTitle(CallbackInfo ci) {
			MinecraftClient.getInstance().getWindow().setTitle(ExampleModClient.INSTANCE.getWindowTitle());
		}
}


