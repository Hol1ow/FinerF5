package com.pang.finerf5;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.option.Perspective;
import net.minecraft.client.util.InputUtil;


public class FinerF5Client implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		KeyBinding fp2tpbToggle = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"Toggle between 1st & 3rd back", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F6, "Finer F5"));

		KeyBinding fp2tpfToggle = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"Toggle between 1st & 3rd front", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F7, "Finer F5"));

		KeyBinding tpb2tpfToggle = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"Toggle between 3rd back & front", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "Finer F5"));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while(fp2tpbToggle.wasPressed()) {
				if (client.options.getPerspective() != Perspective.FIRST_PERSON) {
					client.options.setPerspective(Perspective.FIRST_PERSON);
				} else {
					client.options.setPerspective(Perspective.THIRD_PERSON_BACK);
				}
			}

			while(fp2tpfToggle.wasPressed()) {
				if (client.options.getPerspective() != Perspective.FIRST_PERSON) {
					client.options.setPerspective(Perspective.FIRST_PERSON);
				} else {
					client.options.setPerspective(Perspective.THIRD_PERSON_FRONT);
				}
			}

			while(tpb2tpfToggle.wasPressed()) {
				if (client.options.getPerspective() != Perspective.THIRD_PERSON_BACK) {
					client.options.setPerspective(Perspective.THIRD_PERSON_BACK);
				} else {
					client.options.setPerspective(Perspective.THIRD_PERSON_FRONT);
				}
			}
		});
	}
}