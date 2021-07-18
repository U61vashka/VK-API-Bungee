package com.ubivashka.vk.vklisteners;

import java.util.concurrent.TimeUnit;

import com.google.gson.JsonObject;
import com.ubivashka.vk.VKAPI;
import com.ubivashka.vk.events.VKJsonEvent;
import com.ubivashka.vk.runnables.ProxyRunnable;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.exceptions.LongPollServerKeyExpiredException;
import com.vk.api.sdk.objects.callback.longpoll.responses.GetLongPollEventsResponse;
import com.vk.api.sdk.objects.groups.responses.GetLongPollServerResponse;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.Event;

public class LongpollAPI {
	private VKAPI plugin;

	private String ts;

	private GetLongPollServerResponse longserver;

	public LongpollAPI(VKAPI plugin) {
		this.plugin = plugin;
		updateKey();
		ts = longserver.getTs();
		startEventListener();
	}

	private void startEventListener() {
		System.out.println(ChatColor.GREEN + "LongPool event listener enabled");
		new ProxyRunnable() {
			public void run() {
				try {
					GetLongPollEventsResponse events = (GetLongPollEventsResponse) plugin.vk.longPoll()
							.getEvents(longserver.getServer(), longserver.getKey(), ts).execute();
					for (JsonObject json : events.getUpdates()) {
						LongpollAPI.this.callEvent(json);
					}
					ts = events.getTs();
				} catch (LongPollServerKeyExpiredException e) {
					updateKey();
				} catch (NumberFormatException | ApiException | com.vk.api.sdk.exceptions.ClientException e) {
					e.printStackTrace();
					cancel();
				}
			}
		}.runAfterEvery(plugin, 0L, plugin.getConfig().getInt("settings.delay"), TimeUnit.SECONDS);
	}

	private void callEvent(JsonObject json) {
		VKJsonEvent jsonEvent = new VKJsonEvent(json);
		plugin.getProxy().getPluginManager().callEvent((Event) jsonEvent);
		plugin.callbackAPI.parse(json);
	}

	private void updateKey() {
		try {
			this.longserver = (GetLongPollServerResponse) plugin.vk.groupsLongPoll()
					.getLongPollServer(plugin.actor, plugin.actor.getGroupId().intValue()).execute();
		} catch (ApiException | com.vk.api.sdk.exceptions.ClientException e) {
			e.printStackTrace();
			return;
		}
	}
}
