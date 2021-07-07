package com.ubivashka.vk.events;

import com.vk.api.sdk.objects.callback.PhotoCommentDelete;
import net.md_5.bungee.api.plugin.Event;

public class VKPhotoCommentDeleteEvent extends Event {
  private PhotoCommentDelete photoCommentDelete;
  
  public VKPhotoCommentDeleteEvent(PhotoCommentDelete photoCommentDelete) {
    setPhotoCommentDelete(photoCommentDelete);
  }
  
  public PhotoCommentDelete getPhotoCommentDelete() {
    return this.photoCommentDelete;
  }
  
  public void setPhotoCommentDelete(PhotoCommentDelete photoCommentDelete) {
    this.photoCommentDelete = photoCommentDelete;
  }
}
