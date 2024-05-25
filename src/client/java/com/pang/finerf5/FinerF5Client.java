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
			"key.finerf5.1to3bToggle", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F6, "category.finerf5.text"));

		KeyBinding fp2tpfToggle = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.finerf5.1to3fToggle", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F7, "category.finerf5.text"));

		KeyBinding tpb2tpfToggle = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.finerf5.3bto3fToggle", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "category.finerf5.text"));

		KeyBinding fpSwitch = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.finerf5.1Switch", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "category.finerf5.text"));

		KeyBinding tpbSwitch = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.finerf5.3bSwitch", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "category.finerf5.text"));

		KeyBinding tbfSwitch = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.finerf5.3fSwitch", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "category.finerf5.text"));

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

			while(fpSwitch.wasPressed()) {
				if (client.options.getPerspective() != Perspective.FIRST_PERSON) {
					client.options.setPerspective(Perspective.FIRST_PERSON);
				}
			}

			while(tpbSwitch.wasPressed()) {
				if (client.options.getPerspective() != Perspective.THIRD_PERSON_BACK) {
					client.options.setPerspective(Perspective.THIRD_PERSON_BACK);
				}
			}

			while(tbfSwitch.wasPressed()) {
				if (client.options.getPerspective() != Perspective.THIRD_PERSON_FRONT) {
					client.options.setPerspective(Perspective.THIRD_PERSON_FRONT);
				}
			}
		});
	}
}