package com.example.module.misc;

import com.example.module.Mod;
import com.example.module.ModuleManager;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;

public class ModInformation extends Mod{

    public ModInformation() {
        super("ModInformation", "Displays information about all mods", Category.MISC);
    }
    @Override
    public void onEnable() {
        StringBuilder info = new StringBuilder();
        //we add the name, description, and keybind, and enabled status of each mod to the info string
        for (Mod mod : ModuleManager.INSTANCE.getModules()) {
            info.append(mod.getName()).append(" | ").append(mod.getDescription()).append(" | ").append(mod.getKey()).append(" | ").append(mod.isEnabled()).append("\n");
        }
        //we send the info string to the chat
        ClientPlayerEntity player = mc.player;
        assert player != null;
        player.sendMessage(Text.of(info.toString()), false);
        //we disable the mod
        this.toggle();



    }

}
