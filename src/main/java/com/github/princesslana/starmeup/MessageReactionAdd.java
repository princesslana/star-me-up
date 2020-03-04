package com.github.princesslana.starmeup;

import com.eclipsesource.json.JsonObject;

public class MessageReactionAdd {

  private final JsonObject d;

  public MessageReactionAdd(JsonObject d) {
    this.d = d;
  }

  public String getChannelId() {
    return d.getString("channel_id", "");
  }

  public String getMessageId() {
    return d.getString("message_id", "");
  }

  public boolean isStar() {
    return d.get("emoji").asObject().getString("name", "").equals(Config.getStar());
  }

}
