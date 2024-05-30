package com.pang.finerf5;


import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;


public class FinerF5Client implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		//register keys
		PerspectiveKeyBinding.register();

		//register command
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registerAccess) -> PerspectiveCommand.register(dispatcher));
	}
}