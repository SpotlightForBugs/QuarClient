package com.example.module.settings;

public class NumberSetting extends Setting {

  private double value;
  private double min;
  private double max;
  private double increment;

  public NumberSetting(String name, double defaultValue, double min, double max, double increment) {
    super(name);
    this.value = defaultValue;
    this.min = min;
    this.max = max;
    this.increment = increment;
  }

  public static double clamp(double value, double min, double max) {
    value = Math.max(value, min);
    value = Math.min(max, value);
    return value;
  }

  public double getValue() {
    return value;
  }

  public float getValueFloat() {
    return (float) value;
  }

  public int getValueInt() {
    return (int) value;
  }

  public void setValue(double value) {
    value = clamp(value, this.min, this.max);
    value = Math.round(value * (1.0 / this.increment)) / (1.0 / this.increment);
    this.value = value;
  }

  public double getIncrement() {
    return increment;
  }

  public void increment(boolean positive) {
    if (positive) {
      setValue(this.value + getIncrement());
    } else {
      setValue(this.value - getIncrement());
    }
  }

  public double getMin() {
    return min;
  }

  public double getMax() {
    return max;
  }
}
