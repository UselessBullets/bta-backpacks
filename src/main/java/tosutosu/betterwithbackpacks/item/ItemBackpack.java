package tosutosu.betterwithbackpacks.item;

import net.minecraft.client.Minecraft;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import tosutosu.betterwithbackpacks.gui.guiscreen.GuiBackpack;

public class ItemBackpack extends Item{
    public final int backpackSize;
    public ItemBackpack(String name, int id) {
        this(name, id, 9);
    }
    public ItemBackpack(String name, int id, int backpackSize) {
        super(name, id);
        this.maxStackSize = 1;
        this.backpackSize = backpackSize;
    }
    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        Minecraft mc = Minecraft.getMinecraft(this);
        mc.displayGuiScreen(new GuiBackpack(entityplayer, itemstack));
        return itemstack;
    }
}
