package com.github.princesslana.starmeup;

import com.eclipsesource.json.JsonObject;

public class MessageReactionAdd {

  private final JsonObject d;

  public MessageReactionAdd(JsonObject d) {
    this.d = d;
  }

  public boolean isStar() {
    return d.get("emoji").asObject().getString("name", "").equals("\u2B50");
  }

}
