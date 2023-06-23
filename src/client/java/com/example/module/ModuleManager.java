package com.example.module;

import com.example.module.misc.AntiAFK;
import com.example.module.misc.ModInformation;
import com.example.module.misc.Panic;
import com.example.module.movement.*;
import com.example.module.render.Xray;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import java.util.ArrayList;
import java.util.List;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;

public class ModuleManager {

  public static final ModuleManager INSTANCE = new ModuleManager();
  private List<Mod> mods = new ArrayList<Mod>();

  public ModuleManager() {

    addModules();
    registerCommands();
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
    mods.add(new VanillaFlight());
    mods.add(new Jetpack());
    mods.add(new Spider());
    mods.add(new AntiAFK());
    mods.add(new Xray());
    mods.add(new NoFall());
    mods.add(new Step());
    mods.add(new ModInformation());
    mods.add(new Panic(mods));
    mods.add(
        new Mod("HUD", "the HUD", Mod.Category.MISC) {
          @Override
          public void onEnable() {
            super.onEnable();
          }

          @Override
          public void onDisable() {
            super.onDisable();
          }
        });
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

  public List<Mod> getModulesInCategory(Mod.Category category) {
    List<Mod> modsInCategory = new ArrayList<Mod>();
    for (Mod mod : mods) {
      if (mod.getCategory() == category) {
        modsInCategory.add(mod);
      }
    }
    return modsInCategory;
  }

  /**
   * The registerCommands method is used to register commands for each module. This method is called
   * in the constructor of the ModuleManager class.
   */
  private void registerCommands() { // TODO: Add command arguments, And Colors
    for (Mod mod : this.getModules()) {
      LiteralArgumentBuilder<FabricClientCommandSource> command =
          LiteralArgumentBuilder.literal(mod.getName());
      command.executes(
          context -> {
            ClientPlayerEntity player = context.getSource().getPlayer();
            if (mod.isEnabled()) {
              player.sendMessage(Text.of("[QUARCLIENT] Disabling " + mod.getName()), false);
            } else {
              player.sendMessage(Text.of("[QUARCLIENT] Enabling " + mod.getName()), false);
            }
            mod.toggle();
            return 1;
          });
      if (ClientCommandManager.getActiveDispatcher() != null) {
        ClientCommandManager.getActiveDispatcher().register(command);
      }
    }
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
