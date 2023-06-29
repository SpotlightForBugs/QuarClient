package com.example.module.misc;

import com.example.module.Mod;
import com.example.module.Mod.Category;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.Item;

public class FastEat extends Mod {

  private boolean eating;

  public FastEat() {
    super("FastEat", "Allows faster consumption of food", Category.MISC);
  }

  @Override
  public void onEnable() {
    eating = false;
  }

  @Override
  public void onDisable() {
    stopEating();
  }

  @Override
  public void onTick() {
    updateEating();
    if (eating) {
      ClientPlayerEntity player = MinecraftClient.getInstance().player;
      if (player != null && player.isUsingItem() && player.getItemUseTimeLeft() < 10) {
        // Speed up the food consumption by setting the remaining use time to 10 ticks
        player
            .getHungerManager()
            .eat(player.getMainHandStack().getItem(), player.getMainHandStack());
        player.getItemUseTimeLeft();
      } else {
        stopEating();
      }
    }
  }

  public void updateEating() {
    if (!eating) {
      ClientPlayerEntity player = MinecraftClient.getInstance().player;
      if (player != null) {
        Item itemInHand = player.getMainHandStack().getItem();
        if (itemInHand.isFood()) {
          eating = true;
          player.getItemUseTimeLeft();
        }
      }
    }
  }

  public void stopEating() {
    if (eating) {
      eating = false;
      ClientPlayerEntity player = MinecraftClient.getInstance().player;
      if (player != null && player.isUsingItem()) {
        player.stopUsingItem();
      }
    }
  }
}
