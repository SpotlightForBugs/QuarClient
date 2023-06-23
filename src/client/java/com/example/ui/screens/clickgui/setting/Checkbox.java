package com.example.ui.screens.clickgui.setting;

import com.example.module.settings.BooleanSetting;
import com.example.module.settings.Setting;
import com.example.ui.screens.clickgui.ModuleButton;
import java.awt.*;
import net.minecraft.client.gui.DrawContext;

public class Checkbox extends Component {

  private BooleanSetting boolSet = (BooleanSetting) setting;

  public Checkbox(Setting setting, ModuleButton parent, int offset) {
    super(setting, parent, offset);
    this.boolSet = (BooleanSetting) setting;
  }

  @Override
  public void render(DrawContext context, int mouseX, int mouseY, float delta) {
    context.fill(
        parent.parent.x,
        parent.parent.y + parent.offset + offset,
        parent.parent.x + parent.parent.width,
        parent.parent.y + offset + parent.parent.height + parent.offset,
        new Color(0, 0, 0, 160).getRGB());

    int textOffset = (parent.parent.height / 2) - (mc.textRenderer.fontHeight / 2);

    context.drawTextWithShadow(
        mc.textRenderer,
        boolSet.getName() + ": " + boolSet.isEnabled(),
        parent.parent.x + textOffset,
        parent.parent.y + parent.offset + offset + textOffset,
        -1);
    super.render(context, mouseX, mouseY, delta);
  }

  @Override
  public void mouseClicked(double mouseX, double mouseY, int button) {
    if (isHovered(mouseX, mouseY) && button == 0) {
      boolSet.toggle();
    }
    super.mouseClicked(mouseX, mouseY, button);
  }
}
