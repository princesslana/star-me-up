package com.github.princesslana.starmeup;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import com.github.princesslana.smalld.SmallD;

public class Message {

  private final SmallD smalld;
  private final JsonObject json;

  private Message(SmallD smalld, JsonObject json) {
    this.smalld = smalld;
    this.json = json;
  }

  public String getId() {
    return json.getString("id", "");
  }

  public String getUser() {
    String username = json.get("author").asObject().getString("username", "");
    String discriminator = json.get("author").asObject().getString("discriminator", "");

    return username + "#" + discriminator;
  }

  public String getChannelId() {
    return json.getString("channel_id", "");
  }

  public String getContent() {
    return json.getString("content", "");
  }

  public boolean isStarred() {
    JsonArray reactions = json.get("reactions").asArray();

    for (JsonValue v: reactions) {
      JsonObject r = v.asObject();

      boolean isStar = r.get("emoji").asObject().getString("name", "").equals(Config.getStar());
      boolean isMe = r.getBoolean("me", false);

      if (isStar && isMe) {
        return true;
      }
    }
    return false;
  }

  public void star() {
    try {
      String emoji = URLEncoder.encode(Config.getStar(), "utf-8");
      String url = "/channels/" + getChannelId() + "/messages/" + getId() + "/reactions/" + emoji + "/@me";
      smalld.put(url, "");
    } catch (UnsupportedEncodingException e) {
      throw new IllegalStateException(e);
    }
  }


  public static Message get(SmallD smalld, String channelId, String messageId) {
    String url = "/channels/" + channelId + "/messages/" + messageId;
    JsonObject json = Json.parse(smalld.get(url)).asObject();

    return new Message(smalld, json);
  }
}
