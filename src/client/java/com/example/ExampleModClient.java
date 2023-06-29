package com.example;

import com.example.module.Mod;
import com.example.module.ModuleManager;
import com.example.ui.screens.clickgui.ClickGUI;
import io.sentry.Sentry;
import io.sentry.protocol.User;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.network.ClientPlayerEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

public class ExampleModClient implements ClientModInitializer {
  public static final ExampleModClient INSTANCE = new ExampleModClient();
  public ServerCheck serverCheck = new ServerCheck();
  public static ClientPlayerEntity player = MinecraftClient.getInstance().player;

  public Logger logger = LogManager.getLogger(ExampleModClient.class);
  private MinecraftClient mc = MinecraftClient.getInstance();

  public User user;
  private String environment = "";

  @Override
  public void onInitializeClient() {

    if (mc.getSession().getUsername().contains("Player")) {
      logger.info("Welcome to QuarClient (Development)");
      environment = "development";

    } else {
      logger.info("Welcome " + mc.getSession().getUsername() + " to QuarClient");
      environment = "production";
    }

    Sentry.init(
        options -> {
          options.setDsn(
              "https://f6996a6348c04670ba02763e145ee044@o1363527.ingest.sentry.io/4505404898803712");
          options.setTracesSampleRate(1.0);

          options.setEnvironment(environment);
          options.setDebug(environment.equalsIgnoreCase("development"));
        });
    this.user = new User();
    if (this.environment.equalsIgnoreCase("development")) {
      this.user.setUsername("Player");
    } else {
      this.user.setUsername(mc.getSession().getUsername());
      this.user.setId(mc.getSession().getUuid());
    }
  }

  public void onKeyPress(int key, int action) {
    try {

      if (mc.player != null && mc.world != null) {
        if (mc.currentScreen == null || (!(mc.currentScreen instanceof ChatScreen))) {
          if (action == GLFW.GLFW_PRESS) {
            for (Mod mod : ModuleManager.INSTANCE.getModules()) {
              if (mod.getKey() == key) {
                if (mod.isEnabled()) {
                  logger.info("Disabling " + mod.getName());
                } else {
                  logger.info("Enabling " + mod.getName());
                }
                mod.toggle();
              }
            }
            if (key == GLFW.GLFW_KEY_RIGHT_SHIFT) {
              mc.setScreen(ClickGUI.INSTANCE);
            }
          }
        }
      }
    } catch (Exception e) {
      Sentry.captureException(e);
    }
  }

  public void onTick() {
    try {

      if (mc.player != null && mc.world != null) {
        for (Mod mod : ModuleManager.INSTANCE.getEnabledModules()) {
          mod.onTick();
        }
      }
    } catch (Exception e) {

      Sentry.captureException(e);
    }
  }

  public void onLeftClick() {
    try {

      if (mc.player != null) {
        for (Mod mod : ModuleManager.INSTANCE.getEnabledModules()) {
          mod.onLeftClick();
        }
      }
    } catch (Exception e) {
      Sentry.captureException(e);
    }
  }

  public String getWindowTitle() {
    return "QuarClient by SFB";
  }
}
