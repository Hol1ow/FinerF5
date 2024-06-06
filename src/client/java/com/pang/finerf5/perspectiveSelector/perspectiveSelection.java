package com.pang.finerf5.perspectiveSelector;

import net.minecraft.block.Blocks;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.option.Perspective;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;

enum PerspectiveSelection {
    FIRST_PERSON(Text.translatable("perspective.firstPerson"), Perspective.FIRST_PERSON, new ItemStack(Items.ENDER_EYE)),
    THIRD_PERSON_BACK(Text.translatable("perspective.thirdPersonBack"), Perspective.THIRD_PERSON_BACK, new ItemStack(Items.ARMOR_STAND)),
    THIRD_PERSON_FRONT(Text.translatable("perspective.thirdPersonFront"), Perspective.THIRD_PERSON_FRONT, new ItemStack(Blocks.PLAYER_HEAD));

    protected static final PerspectiveSelection[] VALUES = values();

    final Text text;
    final Perspective perspective;
    final ItemStack icon;

    private PerspectiveSelection(final Text text, final Perspective perspective, final ItemStack icon) {
        this.text = text;
        this.perspective = perspective;
        this.icon = icon;
    }

    void renderIcon(DrawContext context, int x, int y) {
        context.drawItem(this.icon, x, y);
    }

    Text getText() {
        return this.text;
    }

    Perspective getPerspective() {
        return this.perspective;
    }

    PerspectiveSelection next() {
        PerspectiveSelection res;
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

    static PerspectiveSelection of(Perspective perspective) {
        PerspectiveSelection res;
        switch (perspective.ordinal()) {
            case 0:
                res = FIRST_PERSON;
                break;
            case 1:
                res = THIRD_PERSON_BACK;
                break;
            case 2:
                res = THIRD_PERSON_FRONT;
                break;
            default:
                throw new MatchException((String)null, (Throwable)null);
        }

        return res;
    }
}
