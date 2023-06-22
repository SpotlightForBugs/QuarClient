package com.example.module.misc;

import com.example.module.Mod;
import com.example.module.ModuleManager;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.client.network.ClientPlayerEntity;

import java.util.List;

public class Panic extends Mod{
private List<Mod> mods;
    public Panic(List<Mod> mods) {
        super("Panic", "Disables all mods", Category.MISC);
        this.mods = mods;
    }

    @Override
    public void onEnable() {
        assert mc.player != null;
        for (Mod mod : mods) {
            if (this != mod) mod.setEnabled(false);
        }

   this.setEnabled(false);
    }


    @Override
    public void onDisable() {
        mc.inGameHud.getChatHud().clear(true);

                }






}
