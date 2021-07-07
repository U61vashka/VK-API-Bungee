package com.ubivashka.vk.events;

import com.google.gson.JsonObject;
import net.md_5.bungee.api.plugin.Event;

public class VKJsonEvent extends Event {
  private JsonObject jsonObject;
  
  public VKJsonEvent(JsonObject jsonObject) {
    setJsonObject(jsonObject);
  }
  
  public JsonObject getJsonObject() {
    return this.jsonObject;
  }
  
  public String getType() {
    return this.jsonObject.get("type").getAsString();
  }
  
  private void setJsonObject(JsonObject jsonObject) {
    this.jsonObject = jsonObject;
  }
}
