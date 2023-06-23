package com.example.ui.screens.clickgui;

import com.example.module.Mod;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;

public class ModuleButton {
    public Mod module;
    public Frame parent;

    public int offset;
    public ModuleButton(Mod module,Frame parent, int offset){

        this.module = module;
        this.parent = parent;
        this.offset = offset;

    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(parent.x,parent.y+offset,parent.x+parent.width,parent.y+offset+parent.height,new Color(0,0,0,160).getRGB());
        if (isHovered(mouseX,mouseY)) {
            context.fill(parent.x, parent.y + offset, parent.x + parent.width, parent.y + offset + parent.height, new Color(0, 0, 0, 160).getRGB());
        }
        context.drawTextWithShadow(parent.mc.textRenderer, module.getName(), parent.x+2, parent.y+offset+2, module.isEnabled() ? Color.RED.getRGB() : Color.WHITE.getRGB());
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        if(button == 0 && isHovered(mouseX,mouseY)) {
            module.toggle();
        }  //TODO: SETTINGS


    }

    public boolean isHovered(double mouseX,double mouseY) {
        return mouseX > parent.x && mouseX < parent.x + parent.width && mouseY > parent.y + offset && mouseY < parent.y + offset + parent.height;
    }

}
