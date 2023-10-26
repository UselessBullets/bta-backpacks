package tosutosu.betterwithbackpacks.gui.guiscreen;

import net.minecraft.client.gui.GuiContainer;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import org.lwjgl.opengl.GL11;
import tosutosu.betterwithbackpacks.BetterWithBackpacks;
import tosutosu.betterwithbackpacks.gui.container.ContainerBackpack;

public class GuiBackpack extends GuiContainer {
    private int GUIx;
    private int GUIy;
    private int rows;
    private int slotsNum;
    private final ContainerBackpack backpack;
    public GuiBackpack(EntityPlayer player, ItemStack stack) {
        super(new ContainerBackpack(player.inventory, stack));
        backpack = (ContainerBackpack)inventorySlots;
    }

    @Override
    public void initGui() {
        GUIx = (this.width - this.xSize) / 2;
        GUIy = (this.height - this.ySize) / 2;
        slotsNum = backpack.backpackInventory.getSizeInventory();
        rows = (int) Math.ceil(slotsNum/9d);
        super.initGui();
    }
    @Override
    protected void drawGuiContainerForegroundLayer() {
        this.fontRenderer.drawString(backpack.backpackInventory.getInvName(), 8, 4, BetterWithBackpacks.GUI_LABEL_COLOR);
        this.fontRenderer.drawString("Inventory", 8, this.ySize - 96 + 2, BetterWithBackpacks.GUI_LABEL_COLOR);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f) {
        GL11.glColor3d(1d,1d, 1d);
        mc.renderEngine.bindTexture(mc.renderEngine.getTexture("/assets/betterwithbackpacks/gui/backpack.png"));
        drawTexturedModalRect(GUIx, GUIy, 0, 0, this.xSize, this.ySize);

        for (int i = 0; i < rows; i++) {
            if (i == rows - 1) {
                drawTexturedModalRect(GUIx + 7,GUIy + 17 + 18 * i, 0, 166, 18 * (slotsNum - (9 * i)), 18);
            } else {
                drawTexturedModalRect(GUIx + 7, GUIy + 17 + 18 * i, 0, 166, 162, 18);
            }
        }
    }
}
