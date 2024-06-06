package com.pang.finerf5.perspectiveSelector;

import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.Perspective;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.NarratorManager;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class PerspectiveSelectionScreen extends Screen {

    static final Identifier SLOT_TEXTURE = new Identifier("gamemode_switcher/slot");
    static final Identifier SELECTION_TEXTURE = new Identifier("gamemode_switcher/selection");
    private static final Identifier TEXTURE = new Identifier("textures/gui/container/gamemode_switcher.png");

    private static final int UI_WIDTH = com.pang.finerf5.perspectiveSelector.PerspectiveSelection.VALUES.length * 31
            - 5;
    private Text selectText;

    private PerspectiveSelection currPerspective = com.pang.finerf5.perspectiveSelector.PerspectiveSelection
            .of(this.getPreviousPerspective());
    private PerspectiveSelection perspective;

    private int lastMouseX;
    private int lastMouseY;
    private boolean mouseUsedForSelection;

    private final List<PerspectiveSelectionScreenButton> perspectiveBtns = Lists.newArrayList();

    public PerspectiveSelectionScreen(Text keyText) {
        super(NarratorManager.EMPTY);
        this.perspective = this.currPerspective;
        this.selectText = Text.translatable("perspective.select_next",
                new Object[] { Text.literal(String.format("[ %s ]", keyText.getString())).formatted(Formatting.AQUA) });
    }

    @SuppressWarnings("resource")
    private Perspective getPreviousPerspective() {
        Perspective pers = MinecraftClient.getInstance().options.getPerspective();
        if (pers != null) {
            return pers;
        } else {
            return Perspective.FIRST_PERSON;
        }
    }

    protected void init() {
        super.init();
        this.perspective = this.currPerspective;

        for (int i = 0; i < PerspectiveSelection.VALUES.length; i++) {
            PerspectiveSelection ps = PerspectiveSelection.VALUES[i];
            this.perspectiveBtns.add(new PerspectiveSelectionScreenButton(this, ps,
                    this.width / 2 - UI_WIDTH / 2 + i * 31, this.height / 2 - 31));
        }
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        if (!this.checkForClose()) {
            context.getMatrices().push();
            RenderSystem.enableBlend();
            int i = this.width / 2 - 62;
            int j = this.height / 2 - 31 - 27;
            context.drawTexture(TEXTURE, i, j, 0.0F, 0.0F, 125, 75, 128, 128);
            context.getMatrices().pop();
            super.render(context, mouseX, mouseY, delta);
            context.drawCenteredTextWithShadow(this.textRenderer, this.perspective.getText(), this.width / 2,
                    this.height / 2 - 31 - 20, -1);
            context.drawCenteredTextWithShadow(this.textRenderer, selectText, this.width / 2, this.height / 2 + 5,
                    16777215);
            if (!this.mouseUsedForSelection) {
                this.lastMouseX = mouseX;
                this.lastMouseY = mouseY;
                this.mouseUsedForSelection = true;
            }

            boolean bl = this.lastMouseX == mouseX && this.lastMouseY == mouseY;
            Iterator<PerspectiveSelectionScreenButton> it = this.perspectiveBtns.iterator();

            while (it.hasNext()) {
                PerspectiveSelectionScreenButton btnWgt = (PerspectiveSelectionScreenButton) it.next();
                btnWgt.render(context, mouseX, mouseY, delta);
                btnWgt.setSelected(this.perspective == btnWgt.perspective);
                if (!bl && btnWgt.isSelected()) {
                    this.perspective = btnWgt.perspective;
                }
            }
        }
    }

    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
    }

    private boolean checkForClose() {
        if (!InputUtil.isKeyPressed(this.client.getWindow().getHandle(), InputUtil.GLFW_KEY_F4)) {
            this.apply();
            this.client.setScreen((Screen) null);
            return true;
        } else {
            return false;
        }
    }

    public boolean shouldPause() {
        return false;
    }

    private void apply() {
        apply(this.client, this.perspective);
    }

    private static void apply(MinecraftClient client, PerspectiveSelection perspectiveSelection) {
        if (client.player != null) {
            client.options.setPerspective(perspectiveSelection.getPerspective());
        }
    }
}
