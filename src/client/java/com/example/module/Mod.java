package com.example.module;

import com.example.module.settings.ModeSetting;
import com.example.module.settings.Setting;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.client.MinecraftClient;

public class Mod {

  private String name;
  private String displayName;
  private String description;
  private Category category;
  private int key;
  private boolean enabled;

  private List<Setting> settings = new ArrayList<>();

  protected MinecraftClient mc = MinecraftClient.getInstance();

  public Mod(String name, String description, Category category) {
    this.name = name;
    this.displayName = name;
    this.description = description;
    this.enabled = false;
    this.category = category;
  }

  public void toggle() {
    this.enabled = !this.enabled;
    if (this.enabled) {
      onEnable();
    } else {
      onDisable();
    }
  }

  public void onEnable() {}

  public void onDisable() {}

  public void onLeftClick() {}

  public void onTick() {}

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Category getCategory() {
    return category;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
    if (this.enabled) {
      onEnable();
    } else {
      onDisable();
    }
  }

  public void setKey(int key) {
    this.key = key;
  }

  public int getKey() {
    return key;
  }

  public String getDisplayName() {
    // Add the active setting to the display name, if there is one
    for (Setting setting : settings) {
      if (setting instanceof ModeSetting modeSetting) {
        if (modeSetting.isMode(modeSetting.getMode())) {
          return displayName + " " + modeSetting.getMode();
        }
      }
    }
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public List<Setting> getSettings() {
    return settings;
  }

  public void setSettings(List<Setting> settings) {
    this.settings = settings;
  }

  public void addSetting(Setting setting) {
    this.settings.add(setting);
  }

  public void addSettings(Setting... settings) {
    this.settings.addAll(Arrays.asList(settings));
  }

  public Setting getSetting(String name) {
    for (Setting setting : settings) {
      if (setting.getName().equals(name)) {
        return setting;
      }
    }
    return null;
  }

  public enum Category {
    COMBAT("Combat"),
    MOVEMENT("Movement"),
    RENDER("Render"),
    EXPLOIT("Exploit"),
    MISC("Misc");

    public String name;

    Category(String name) {
      this.name = name;
    }
  }
}
