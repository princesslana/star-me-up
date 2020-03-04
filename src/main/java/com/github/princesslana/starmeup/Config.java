package com.github.princesslana.starmeup;

import java.util.Optional;

public class Config {

  public String getToken() {
    return getString("SMU_TOKEN");
  }

  public String getChannelPostId() {
    return getString("SMU_CHANNEL_ID");
  }

  private String getString(String key) {
    return Optional.ofNullable(System.getenv(key))
      .orElseThrow(() -> new IllegalStateException("No config for " + key));
  }

}

