package com.pang.finerf5;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;

import net.minecraft.client.option.Perspective;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;

public class PerspectiveArgumentType implements ArgumentType<Perspective> {
    private static final Collection<String> EXAMPLES;
    private static final String[] VALUES;
    private static final DynamicCommandExceptionType INVALID_PESPECTIVE_EXCEPTION;

    @Override
    public Perspective parse(StringReader reader) throws CommandSyntaxException {
        String string = reader.readUnquotedString();
        Perspective persp = null;
        switch (string) {
            case "firstPerson":
                persp = Perspective.FIRST_PERSON; break;
            case "thirdPersonBack":
                persp = Perspective.THIRD_PERSON_BACK; break;
            case "thirdPersonFront":
                persp = Perspective.THIRD_PERSON_FRONT; break;
            default:
                break;
        }
        if (persp == null) {
           throw INVALID_PESPECTIVE_EXCEPTION.createWithContext(reader, string);
        } else {
           return persp;
        }  
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
      return context.getSource() instanceof CommandSource ? CommandSource.suggestMatching(Arrays.stream(VALUES), builder)
       : Suggestions.empty();
    }

    @Override
    public Collection<String> getExamples() {
        return EXAMPLES;
    }

    public static PerspectiveArgumentType perspective() {
        return new PerspectiveArgumentType();
    }

    static {
        EXAMPLES = List.of("firstPerson, thirdPersonBack, thirdPersonFront");
        VALUES = new String[] {"firstPerson", "thirdPersonBack", "thirdPersonFront"};
        INVALID_PESPECTIVE_EXCEPTION = new DynamicCommandExceptionType((perspective) -> {
            return Text.stringifiedTranslatable("argument.perspective.invalid", new Object[]{perspective});
         });
    }
    
}
