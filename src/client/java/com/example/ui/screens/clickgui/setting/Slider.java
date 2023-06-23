package com.example.ui.screens.clickgui.setting;

import com.example.module.settings.NumberSetting;
import com.example.module.settings.Setting;
import com.example.ui.screens.clickgui.ModuleButton;
import net.minecraft.client.gui.DrawContext;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Slider extends Component{

    private boolean sliding = false;

    public NumberSetting numSet = (NumberSetting) setting;
    public Slider(Setting setting, ModuleButton parent, int offset) {
        super(setting, parent, offset);
        this.numSet = (NumberSetting) setting;
    }


    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(
                parent.parent.x,
                parent.parent.y + parent.offset + offset,
                parent.parent.x + parent.parent.width,
                parent.parent.y + offset + parent.parent.height + parent.offset,
                new Color(0, 0, 0, 160).getRGB());


        double diff = Math.min(parent.parent.width, Math.max(0, mouseX - parent.parent.x));

        int renderWidth = (int) (parent.parent.width * (numSet.getValue() - numSet.getMin()) / (numSet.getMax() - numSet.getMin()));

        context.fill(
                parent.parent.x,
                parent.parent.y + parent.offset + offset,
                parent.parent.x + renderWidth,
                parent.parent.y + offset + parent.parent.height + parent.offset,
                Color.RED.getRGB());


        if (sliding){
            if (diff == 0){
                numSet.setValue(numSet.getMin());
        } else {
                double value = diff / parent.parent.width * (numSet.getMax() - numSet.getMin()) + numSet.getMin();
                numSet.setValue(roundToPlace(value,2));
            }
        }


        int textOffset = (parent.parent.height / 2) - (mc.textRenderer.fontHeight / 2);

        context.drawTextWithShadow(mc.textRenderer,numSet.getName() + ": " + roundToPlace(numSet.getValue(),2), parent.parent.x + textOffset, parent.parent.y + parent.offset+offset + textOffset, -1);


        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {
        sliding = false;
        super.mouseReleased(mouseX, mouseY, button);
    }


    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        if(isHovered(mouseX, mouseY)) {
            sliding = true;
        } else {
            sliding = false;
        }
        super.mouseClicked(mouseX, mouseY, button);
    }

    private double roundToPlace(double value, int place){
        if(place < 0){return value;}
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(place, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
