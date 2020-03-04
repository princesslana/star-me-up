package com.github.princesslana.starmeup;

import java.util.Optional;

public class Config {

  private Config() { }

  public static String getToken() {
    return getString("SMU_TOKEN");
  }

  public static String getChannelPostId() {
    return getString("SMU_CHANNEL_ID");
  }

  public static String getStar() {
    return "\u2b50";

  }

  private static String getString(String key) {
    return Optional.ofNullable(System.getenv(key))
      .orElseThrow(() -> new IllegalStateException("No config for " + key));
  }
}
