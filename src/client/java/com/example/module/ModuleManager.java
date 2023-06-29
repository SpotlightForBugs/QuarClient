package com.example.module;

import com.example.module.combat.Criticals;
import com.example.module.misc.AntiAFK;
import com.example.module.misc.FastEat;
import com.example.module.misc.ModInformation;
import com.example.module.misc.Panic;
import com.example.module.movement.*;
import com.example.module.render.Xray;
import io.sentry.Sentry;
import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

  public static final ModuleManager INSTANCE = new ModuleManager();
  private List<Mod> mods = new ArrayList<Mod>();

  public ModuleManager() {

    addModules();
    enableMod("Panic");
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
    Sentry.configureScope(scope -> scope.setTransaction("addModules"));
    mods.add(new Flight());
    mods.add(new Jetpack());
    mods.add(new Spider());
    mods.add(new AntiAFK());
    mods.add(new Xray());
    mods.add(new NoFall());
    mods.add(new Step());
    mods.add(new ModInformation());
    mods.add(new Sprint());
    mods.add(new FastEat());
    mods.add(new Criticals());
    //-----------------------\\
    //MUST BE LAST
    mods.add(new Panic(mods));
    //MUST BE LAST
  }

  public void disableMod(String name) {
    Sentry.configureScope(scope -> scope.setTag("mod", name));
    Sentry.configureScope(scope -> scope.setTag("enabled", "false"));
    Sentry.configureScope(scope -> scope.setTransaction("disableMod"));

    for (Mod mod : mods) {
      if (mod.getName().equals(name) && mod.isEnabled()) {
        mod.toggle();
      }
    }
  }

  public void enableMod(String name) {
    Sentry.configureScope(scope -> scope.setTag("mod", name));
    Sentry.configureScope(scope -> scope.setTag("enabled", "true"));
    Sentry.configureScope(scope -> scope.setTransaction("enableMod"));

    for (Mod mod : mods) {
      if (mod.getName().equals(name)) {
        if (!mod.isEnabled()) mod.toggle();
      }
    }
  }

  public List<Mod> getModulesInCategory(Mod.Category category) {
    List<Mod> modsInCategory = new ArrayList<Mod>();
    for (Mod mod : mods) {
      if (mod.getCategory() == category) {
        modsInCategory.add(mod);
      }
    }
    return modsInCategory;
  }

  public Mod getModule(String name) {
    for (Mod mod : mods) {
      if (mod.getName().equals(name)) {
        return mod;
      }
    }
    return null;
  }
}
