package com.example.module;

import net.minecraft.client.MinecraftClient;

public class Mod {

  private String name;
  private String description;
  private Category category;
  private int key;
  private boolean enabled;

  protected MinecraftClient mc = MinecraftClient.getInstance();

  public Mod(String name, String description, Category category) {
    this.name = name;
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
