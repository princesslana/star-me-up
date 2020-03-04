package com.github.princesslana.starmeup;

import java.util.function.Consumer;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.github.princesslana.smalld.SmallD;

public class StarMeUp implements Consumer<SmallD> {

  public void accept(SmallD smalld) {
    smalld.onGatewayPayload(p -> {
      JsonObject json = Json.parse(p).asObject();

      ifMessageReactionAdd(json, ra -> {
        if (ra.isStar()) {
          Message m = Message.get(smalld, ra.getChannelId(), ra.getMessageId());

          if (!m.isStarred()) {
            String content = m.getUser() + " said:\n> " + m.getContent();
            smalld.post(
              "/channels/" + Config.getChannelPostId() + "/messages",
              Json.object().add("content", content).toString());

            m.star();
          }
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
    SmallD.run(Config.getToken(), new StarMeUp());
  }
}
