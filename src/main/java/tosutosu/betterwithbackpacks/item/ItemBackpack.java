package tosutosu.betterwithbackpacks.item;

import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import tosutosu.betterwithbackpacks.BetterWithBackpacks;
import tosutosu.betterwithbackpacks.IPlayerDisplay;

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
    public ItemStack onUseItem(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        if (BetterWithBackpacks.ENABLE_BACKPACKS) {
            ((IPlayerDisplay) entityplayer).displayGUIBackpack(itemstack);
        }
        return itemstack;
    }
}
