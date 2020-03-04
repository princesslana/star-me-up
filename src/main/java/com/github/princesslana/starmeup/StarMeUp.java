package com.github.princesslana.starmeup;

import java.util.function.Consumer;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.github.princesslana.smalld.SmallD;

public class StarMeUp implements Consumer<SmallD> {

  private final Config config;

  private StarMeUp(Config config) {
    this.config = config;
  }

  public void accept(SmallD smalld) {
    smalld.onGatewayPayload(p -> {
      JsonObject json = Json.parse(p).asObject();

      ifMessageReactionAdd(json, ra -> {
        if (ra.isStar()) {
          smalld.post(
            "/channels/" + config.getChannelPostId() + "/messages",
            Json.object().add("content", "star").toString());
        }
      });
    });
  }

  private static void ifMessageReactionAdd(JsonObject json, Consumer<MessageReactionAdd> f) {
    if (json.getInt("op", -1) == 0 && json.getString("t", "").equals("MESSAGE_REACTION_ADD")) {
      f.accept(new MessageReactionAdd(json.get("d").asObject()));
    }
  }

  public static void main(String[] args) {
    Config config = new Config();

    SmallD.run(config.getToken(), new StarMeUp(config));
  }
}
