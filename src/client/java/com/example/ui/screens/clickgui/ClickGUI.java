package com.example.ui.screens.clickgui;

import com.example.module.Mod;

import java.util.ArrayList;
import java.util.List;

import com.example.module.ModuleManager;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ClickGUI extends Screen {

    public static final ClickGUI INSTANCE = new ClickGUI();
    private List<Frame> frames;

    private ClickGUI() {
        super(Text.literal("Click GUI"));
        frames = new ArrayList<>();
        int offset = 20;
        for (Mod.Category category : Mod.Category.values()) {
            frames.add(new Frame(category, offset, 20, 100, 20));
            offset += 120;
        }


    }


    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {

        for (Frame frame : frames) {
            frame.render(context, mouseX, mouseY, delta);
            frame.updatePosition(mouseX, mouseY);
        }
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (Frame frame : frames) {
            frame.getButtons().forEach(moduleButton -> moduleButton.mouseClicked(mouseX, mouseY, button));
            frame.mouseClicked(mouseX, mouseY, button);
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        for (Frame frame : frames) {
            frame.mouseReleased(mouseX, mouseY, button);
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }
}
