package com.ubivashka.vk.events;

import com.vk.api.sdk.objects.callback.MessageDeny;
import net.md_5.bungee.api.plugin.Event;

public class VKMessageDenyEvent extends Event {
  private MessageDeny messageDeny;
  
  public VKMessageDenyEvent(MessageDeny messageAllow) {
    setMessageDeny(messageAllow);
  }
  
  public MessageDeny getMessageDeny() {
    return this.messageDeny;
  }
  
  public Integer getUser() {
    return this.messageDeny.getUserId();
  }
  
  private void setMessageDeny(MessageDeny messageAllow) {
    this.messageDeny = messageAllow;
  }
}
