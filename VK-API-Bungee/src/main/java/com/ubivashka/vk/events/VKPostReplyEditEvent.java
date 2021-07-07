package com.ubivashka.vk.events;

import com.vk.api.sdk.objects.wall.WallComment;
import net.md_5.bungee.api.plugin.Event;

public class VKPostReplyEditEvent extends Event {
  private WallComment postComment;
  
  public VKPostReplyEditEvent(WallComment postComment) {
    setPostComment(postComment);
  }
  
  public WallComment getPostComment() {
    return this.postComment;
  }
  
  public void setPostComment(WallComment postComment) {
    this.postComment = postComment;
  }
}
