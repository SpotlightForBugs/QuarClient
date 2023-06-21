package com.example.module;

import com.example.module.movement.NoFall;
import com.example.module.movement.VanillaFlight;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

    public static final ModuleManager INSTANCE = new ModuleManager();
    private List<Mod> mods = new ArrayList<Mod>();

    public ModuleManager() {

        addModules();

    }



    public List<Mod> getModules() {
        return mods;
    }

    public List<Mod> getEnabledModules() {
        List<Mod> enabledMods = new ArrayList<Mod>();
        for (Mod mod : mods) {
            if (mod.isEnabled()) {
                enabledMods.add(mod);
            }
        }
        return enabledMods;
    }
    private void addModules() {
        mods.add(new VanillaFlight());
        mods.add(new NoFall());

    }
}
