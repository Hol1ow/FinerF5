package com.pang.finerf5;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;


public class FinerF5Client implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		KeyBinding ff2tfToggle = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"1st to 3rd Toggle", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F5, "Finer F5"));

		KeyBinding ff2sfToggle = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"1st to 2nd Toggle", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F6, "Finer F5"));

		KeyBinding tf2sfToggle = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"3rd to 2nd Toggle", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F7, "Finer F5"));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while(ff2tfToggle.wasPressed()) {
				client.player.sendMessage(Text.literal("Key 1 was pressed!"), false);
			}

			while(ff2sfToggle.wasPressed()) {
				client.player.sendMessage(Text.literal("Key 2 was pressed!"), false);
			}

			while(tf2sfToggle.wasPressed()) {
				client.player.sendMessage(Text.literal("Key 3 was pressed!"), false);
			}
		});
	}
}