package com.pang.finerf5.perspectiveSelector;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;

@Environment(EnvType.CLIENT)
public class PerspectiveSelectionScreenButton extends ClickableWidget {

    final PerspectiveSelection perspective;
    private boolean selected;

    public PerspectiveSelectionScreenButton(final PerspectiveSelectionScreen perspectiveSelectionScreen, final PerspectiveSelection perspective, final int x, final int y) {
        super(x, y, 26, 26, perspective.getText());
        this.perspective = perspective;
    }

    @Override
    protected void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        this.drawBackground(context);
        this.perspective.renderIcon(context, this.getX() + 5, this.getY() + 5);
        if (this.selected) {
            this.drawSelectionBox(context);
        }
    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {
        this.appendDefaultNarrations(builder);
    }

    public boolean isSelected() {
        return super.isSelected() || this.selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    private void drawBackground(DrawContext context) {
        context.drawGuiTexture(PerspectiveSelectionScreen.SLOT_TEXTURE, this.getX(), this.getY(), 26, 26);
    }

    private void drawSelectionBox(DrawContext context) {
        context.drawGuiTexture(PerspectiveSelectionScreen.SELECTION_TEXTURE, this.getX(), this.getY(), 26, 26);
    }
    
}
