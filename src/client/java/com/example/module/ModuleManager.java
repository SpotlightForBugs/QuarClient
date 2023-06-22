package com.example.module;

import com.example.module.misc.AntiAFK;
import com.example.module.misc.ModInformation;
import com.example.module.misc.Panic;
import com.example.module.movement.*;
import com.example.module.render.Xray;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;


import java.util.ArrayList;
import java.util.List;

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






    /**
     * The registerCommands method is used to register commands for each module.
     * This method is called in the constructor of the ModuleManager class.
     */
    private void registerCommands() { //TODO: Add command arguments, And Colors
        for (Mod mod : this.getModules()) {
            LiteralArgumentBuilder<FabricClientCommandSource> command = LiteralArgumentBuilder.literal(mod.getName());
            command.executes(context -> {
                ClientPlayerEntity player = context.getSource().getPlayer();
                if (mod.isEnabled()) {
                    player.sendMessage(Text.of("[QUARCLIENT] Disabling " + mod.getName()), false);
                } else {
                    player.sendMessage(Text.of("[QUARCLIENT] Enabling " + mod.getName()), false);
                }
                mod.toggle();
                return 1;
            });
            if (ClientCommandManager.getActiveDispatcher() != null){
                ClientCommandManager.getActiveDispatcher().register(command);
            }else {

                while (ClientCommandManager.getActiveDispatcher() == null){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } registerCommands();

            }
        }



} }
