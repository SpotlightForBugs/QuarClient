package com.example.module;

import com.example.module.misc.AntiAFK;
import com.example.module.movement.*;
import com.example.module.render.Xray;

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
        mods.add(new Jetpack());
        mods.add(new Spider());
        mods.add(new AntiAFK());
        mods.add(new Xray());
        mods.add(new NoFall());
        mods.add(new Step());


    }

    public void disableMod(String name) {
        for (Mod mod : mods) {
            if (mod.getName().equals(name)) {
                            mod.toggle();
            }
        }
    }

    public void enableMod(String name) {

        for (Mod mod : mods) {
            if (mod.getName().equals(name)) {
                if (!mod.isEnabled()) mod.toggle();
            }
        }

    }
}
