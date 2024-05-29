package com.pang.finerf5;

import net.fabricmc.api.ClientModInitializer;


public class FinerF5Client implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		//register keys
		PerspectiveKeyBinding.register();
	}
}