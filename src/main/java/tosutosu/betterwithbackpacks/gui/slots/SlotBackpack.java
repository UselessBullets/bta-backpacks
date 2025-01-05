package tosutosu.betterwithbackpacks.gui.slots;

import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.container.Container;
import net.minecraft.core.player.inventory.slot.Slot;
import tosutosu.betterwithbackpacks.item.ItemBackpack;
import tosutosu.betterwithbackpacks.item.ItemBackpackInventory;

public class SlotBackpack extends Slot {
    public SlotBackpack(final Container inventory, final int id, final int x, final int y) {
        super(inventory, id, x, y);
    }
    @Override
    public boolean mayPlace(final ItemStack itemstack) {
        return !(itemstack.getItem() instanceof ItemBackpack);
    }
}
