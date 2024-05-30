package com.pang.finerf5;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.*;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.option.Perspective;

public class PerspectiveCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
		dispatcher.register(literal("perspective")
		.then(argument("perspective", PerspectiveArgumentType.perspective())
        .executes(context -> execute(context))
        ));
    }

    @SuppressWarnings("resource")
    private static int execute(CommandContext<FabricClientCommandSource> context) {
        Perspective p = context.getArgument("perspective", Perspective.class);
        context.getSource().getClient().options.setPerspective(p);
        return 1;
    }
}
