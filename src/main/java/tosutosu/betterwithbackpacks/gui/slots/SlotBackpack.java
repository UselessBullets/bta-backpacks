package tosutosu.betterwithbackpacks.gui.slots;

import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.slot.Slot;
import tosutosu.betterwithbackpacks.item.ItemBackpack;
import tosutosu.betterwithbackpacks.item.ItemBackpackInventory;

public class SlotBackpack extends Slot {
    public SlotBackpack(ItemBackpackInventory inventory, int id, int x, int y) {
        super(inventory, id, x, y);
    }
    @Override
    public boolean mayPlace(ItemStack itemstack) {
        return itemstack.getItem() != null && !(itemstack.getItem() instanceof ItemBackpack);
    }
}
