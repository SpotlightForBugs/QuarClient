package com.example.ui.screens.clickgui;

import com.example.module.Mod;
import com.example.module.ModuleManager;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.example.ui.screens.clickgui.setting.Component;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class Frame {

  public int x, y, width, height, dragX, dragY;
  public Mod.Category category;

  public boolean dragging, extended;
  private List<ModuleButton> buttons;

  protected MinecraftClient mc = MinecraftClient.getInstance();

  public Frame(Mod.Category category, int x, int y, int width, int height) {
    this.category = category;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.dragging = false;
    this.extended = true;
    this.buttons = new ArrayList<>();

    int offset = height;
    for (Mod mod : ModuleManager.INSTANCE.getModulesInCategory(category)) {
      buttons.add(new ModuleButton(mod, this, offset));
      offset += height;
    }
  }

  public void render(DrawContext context, int mouseX, int mouseY, float delta) {

    context.fill(x, y, x + width, y + height, Color.RED.getRGB());
    context.drawTextWithShadow(
        mc.textRenderer,
        category.name,
        x + 2,
        y + ((height / 2) - mc.textRenderer.fontHeight / 2),
        -1);
    context.drawTextWithShadow(
        mc.textRenderer,
        extended ? "-" : "+",
        x + width - 2 - mc.textRenderer.getWidth("+"),
        y + ((height / 2) - mc.textRenderer.fontHeight / 2),
        -1);

    if (extended) {

      for (ModuleButton button : buttons) {
        button.render(context, mouseX, mouseY, delta);
      }
    }
  }

  public void mouseClicked(double mouseX, double mouseY, int button) {
    if (isHovered(mouseX, mouseY)) {
      if (button == 0) {
        dragging = true;
        dragX = (int) mouseX - x;
        dragY = (int) mouseY - y;
      } else if (button == 1) {
        extended = !extended;
      }
      if (extended) {
        for (ModuleButton mb : buttons) {
          mb.mouseClicked(mouseX, mouseY, button);

        }
      }
    }
  }

  public void mouseReleased(double mouseX, double mouseY, int button) {
    if (button == 0 && dragging) {
      dragging = false;
    }

    for (ModuleButton mb : buttons) {
      mb.mouseReleased(mouseX, mouseY, button);
    }

  }

  public boolean isHovered(double mouseX, double mouseY) {
    return mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height;
  }

  public void updatePosition(double mouseX, double mouseY) {
    if (dragging) {
      x = (int) mouseX - dragX;
      y = (int) mouseY - dragY;
    }
  }

  public List<ModuleButton> getButtons() {
    return buttons;
  }

  public void updateButtons() {
    int offset = height;
   for (ModuleButton button : buttons) {
    button.offset = offset;
    offset += height;

    if (button.extended){
      for (Component component: button.components){
       if (component.setting.isVisible()) offset += height;
    }

   }
   }
  }

}