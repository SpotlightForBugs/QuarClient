package com.example.module.settings;

import java.util.Arrays;
import java.util.List;

public class ModeSetting extends Setting {

  private List<String> modes;
  private int index;
  private String mode;

  public ModeSetting(String name, String defaultMode, String... modes) {
    super(name);
    this.mode = defaultMode;
    this.modes = Arrays.asList(modes);
    this.index = this.modes.indexOf(defaultMode);
  }

  public List<String> getModes() {
    return modes;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
    this.mode = this.modes.get(index);
  }

  public String getMode() {
    return mode;
  }

  public void setMode(String mode) {
    this.mode = mode;
    this.index = this.modes.indexOf(mode);
  }

  public void cycle() {
    if (this.index < this.modes.size() - 1) {
      this.index++;
    } else {
      this.index = 0;
    }
    this.mode = this.modes.get(this.index);
  }

  public boolean isMode(String mode) {
    return this.mode.equalsIgnoreCase(mode);
  }
}
