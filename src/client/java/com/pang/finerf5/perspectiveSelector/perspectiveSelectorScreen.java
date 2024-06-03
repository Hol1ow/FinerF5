package com.pang.finerf5.perspectiveSelector;

import java.util.List;

import com.google.common.collect.Lists;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.NarratorManager;

@Environment(EnvType.CLIENT)
public class perspectiveSelectorScreen extends Screen {

    private static final int TEXTURE_WIDTH = 128;
    private static final int TEXTURE_HEIGHT = 128;
    private static final int BUTTON_SIZE = 26;
    private static final int ICON_OFFSET = 5;
    
    private perspectiveSelection currPerspective = com.pang.finerf5.perspectiveSelector.perspectiveSelection.of(this.client.options.getPerspective());
    private perspectiveSelection perspective;

    private int lastMouseX;
    private int lastMouseY;
    private boolean mouseUsedForSelection;

    private final List perspectiveBtns = Lists.newArrayList();


    public perspectiveSelectorScreen() {
        super(NarratorManager.EMPTY);
        this.perspective = this.currPerspective;
    }
    
}
