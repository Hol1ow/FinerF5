package com.pang.finerf5.perspectiveSelector;

import java.util.List;

import com.google.common.collect.Lists;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.NarratorManager;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class PerspectiveSelectionScreen extends Screen {

    static final Identifier SLOT_TEXTURE = new Identifier("gamemode_switcher/slot");
    static final Identifier SELECTION_TEXTURE = new Identifier("gamemode_switcher/selection");

    private static final int TEXTURE_WIDTH = 128;
    private static final int TEXTURE_HEIGHT = 128;
    private static final int BUTTON_SIZE = 26;
    private static final int ICON_OFFSET = 5;
    private static final int UI_WIDTH = 5;
    private static Text SELECT_NEXT_TEXT;
    
    private perspectiveSelection currPerspective = com.pang.finerf5.perspectiveSelector.perspectiveSelection.of(this.client.options.getPerspective());
    private perspectiveSelection perspective;

    private int lastMouseX;
    private int lastMouseY;
    private boolean mouseUsedForSelection;

    private final List perspectiveBtns = Lists.newArrayList();


    public PerspectiveSelectionScreen() {
        super(NarratorManager.EMPTY);
        this.perspective = this.currPerspective;
    }

    @SuppressWarnings("unchecked")
    protected void init() {
        super.init();
        this.perspective = this.currPerspective;

        for (int i = 0; i < PerspectiveSelection.VALUES.length; i++) {
            PerspectiveSelection ps = PerspectiveSelection.VALUES[i];
            this.perspectiveBtns.add(new PerspectiveSelectionScreenButton(this, ps, this.width / 2 - UI_WIDTH / 2 + i * 31, this.height / 2 - 31));
        }
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {

    }

    static {
        SELECT_NEXT_TEXT = Text.translatable("debug.gamemodes.select_next", new Object[]{Text.translatable("debug.gamemodes.press_f4").formatted(Formatting.AQUA)});
    }
    
}
