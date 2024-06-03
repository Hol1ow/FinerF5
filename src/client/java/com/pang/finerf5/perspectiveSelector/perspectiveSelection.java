package com.pang.finerf5.perspectiveSelector;

import net.minecraft.block.Blocks;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.option.Perspective;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;

enum perspectiveSelection {
    FIRST_PERSON(Text.translatable("perspective.firstPerson"), "perspective firstPerson", new ItemStack(Items.ENDER_EYE)),
    THIRD_PERSON_BACK(Text.translatable("perspective.thirdPersonBack"), "perspective thirdPersonBack", new ItemStack(Blocks.PLAYER_HEAD)),
    THIRD_PERSON_FRONT(Text.translatable("perspective.thirdPersonFront"), "perspective thirdPersonFront", new ItemStack(Items.ARMOR_STAND));

    final Text text;
    final String command;
    final ItemStack icon;

    private perspectiveSelection(final Text text, final String command, final ItemStack icon) {
        this.text = text;
        this.command = command;
        this.icon = icon;
    }

    void renderIcon(DrawContext context, int x, int y) {
        context.drawItem(this.icon, x, y);
    }

    Text getText() {
        return this.text;
    }

    String getCommand() {
        return this.command;
    }

    perspectiveSelection next() {
        perspectiveSelection res;
        switch (this.ordinal()) {
            case 0:
                res = THIRD_PERSON_FRONT;
                break;
            case 1:
                res = THIRD_PERSON_BACK;
                break;
            case 2:
                res = FIRST_PERSON;
                break;
            default:
                throw new MatchException((String)null, (Throwable)null);
        }

        return res;
    }

    static perspectiveSelection of(Perspective perspective) {
        perspectiveSelection res;
        switch (perspective.ordinal()) {
            case 0:
                res = FIRST_PERSON;
                break;
            case 1:
                res = THIRD_PERSON_BACK;
                break;
            case 2:
                res = THIRD_PERSON_FRONT;
            default:
                throw new MatchException((String)null, (Throwable)null);
        }

        return res;
    }
}
