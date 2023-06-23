package com.example.ui.screens.clickgui;

import com.example.module.Mod;
import com.example.module.settings.*;
import com.example.ui.screens.clickgui.setting.Checkbox;
import com.example.ui.screens.clickgui.setting.Component;
import com.example.ui.screens.clickgui.setting.ModeBox;
import com.example.ui.screens.clickgui.setting.Slider;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.DrawContext;

public class ModuleButton {
  public Mod module;
  public Frame parent;

  public int offset;

  public List<Component> components;

  public boolean extended;

  public ModuleButton(Mod module, Frame parent, int offset) {

    this.module = module;
    this.parent = parent;
    this.offset = offset;
    this.components = new ArrayList<>();
    this.extended = false;

    int setOffset = offset;
    for (Setting setting : module.getSettings()) {
      if (setting instanceof BooleanSetting) {
        components.add(new Checkbox(setting, this, setOffset));
      } else if (setting instanceof ModeSetting) {
        components.add(new ModeBox((ModeSetting) setting, this, setOffset));
      } else if (setting instanceof NumberSetting) {
        components.add(new Slider(setting, this, setOffset));
      }

      setOffset += parent.height;
    }
  }

  public void render(DrawContext context, int mouseX, int mouseY, float delta) {
    context.fill(
        parent.x,
        parent.y + offset,
        parent.x + parent.width,
        parent.y + offset + parent.height,
        new Color(0, 0, 0, 160).getRGB());
    if (isHovered(mouseX, mouseY)) {
      context.fill(
          parent.x,
          parent.y + offset,
          parent.x + parent.width,
          parent.y + offset + parent.height,
          new Color(0, 0, 0, 160).getRGB());
    }
    int textOffset = (parent.height / 2) - (parent.mc.textRenderer.fontHeight / 2);
    context.drawTextWithShadow(
        parent.mc.textRenderer,
        module.getName(),
        parent.x + textOffset,
        parent.y + offset + textOffset,
        module.isEnabled() ? Color.RED.getRGB() : Color.WHITE.getRGB());
    if (extended) {
      for (Component component : components) {
        component.render(context, mouseX, mouseY, delta);
      }
    }
  }

  public void mouseClicked(double mouseX, double mouseY, int button) {

    if (button == 0 && isHovered(mouseX, mouseY)) {
      module.toggle();
    } else if (button == 1 && isHovered(mouseX, mouseY)) {
      extended = !extended;
      parent.updateButtons();
    }
    for (Component component : components) {
      component.mouseClicked(mouseX, mouseY, button);
    }
  }

  public void mouseReleased(double mouseX, double mouseY, int button) {

    for (Component component : components) {
      component.mouseReleased(mouseX, mouseY, button);
    }
  }

  public boolean isHovered(double mouseX, double mouseY) {
    return mouseX > parent.x
        && mouseX < parent.x + parent.width
        && mouseY > parent.y + offset
        && mouseY < parent.y + offset + parent.height;
  }
}
