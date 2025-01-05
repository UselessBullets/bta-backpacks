package tosutosu.betterwithbackpacks.item;

import com.mojang.nbt.tags.CompoundTag;
import com.mojang.nbt.tags.ListTag;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.InventorySorter;
import net.minecraft.core.player.inventory.container.Container;
import tosutosu.betterwithbackpacks.BetterWithBackpacks;

public class ItemBackpackInventory implements Container {
    public final ItemStack stack;
    protected ItemStack[] backpackItemStacks;
    public ItemBackpackInventory(ItemStack stack){
        assert stack.getItem() instanceof ItemBackpack;
        this.stack = stack;
        ItemBackpack itemBackpack = (ItemBackpack) stack.getItem();
        backpackItemStacks = new ItemStack[itemBackpack.backpackSize];
        readFromNBT(stack.getData());
    }
    @Override
    public int getContainerSize(){
        return backpackItemStacks.length;
    }

    @Override
    public ItemStack getItem(int i) {
        return backpackItemStacks[i];
    }

    @Override
    public ItemStack removeItem(int i, int j) {
        if (this.backpackItemStacks[i] != null) {
            if (this.backpackItemStacks[i].stackSize <= j) {
                ItemStack itemstack = this.backpackItemStacks[i];
                this.backpackItemStacks[i] = null;
                return itemstack;
            }
            ItemStack itemstack1 = this.backpackItemStacks[i].splitStack(j);
            if (this.backpackItemStacks[i].stackSize <= 0) {
                this.backpackItemStacks[i] = null;
            }
            return itemstack1;
        }
        return null;
    }

    @Override
    public void setItem(int i, ItemStack itemStack) {
        this.backpackItemStacks[i] = itemStack;
        if (itemStack != null && itemStack.stackSize > this.getMaxStackSize()) {
            itemStack.stackSize = this.getMaxStackSize();
        }
    }

    @Override
    public String getNameTranslationKey() {
        String name = stack.getCustomName();
        if (name != null){
            return name;
        }
        return stack.getDisplayName();
    }

    @Override
    public int getMaxStackSize() {
        return 64;
    }

    @Override
    public void setChanged() {
        writeToNBT(stack.getData());
    }

    public void readFromNBT(CompoundTag nbttagcompound) {
        ListTag nbttaglist = nbttagcompound.getList("Items");
        this.backpackItemStacks = new ItemStack[this.getContainerSize()];
        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            CompoundTag nbttagcompound1 = (CompoundTag)nbttaglist.tagAt(i);
            byte byte0 = nbttagcompound1.getByte("Slot");
            if (byte0 < 0 || byte0 >= this.backpackItemStacks.length) continue;
            this.backpackItemStacks[byte0] = ItemStack.readItemStackFromNbt(nbttagcompound1);
        }
    }
    public void writeToNBT(CompoundTag nbttagcompound) {
        ListTag nbttaglist = new ListTag();
        for (int i = 0; i < this.backpackItemStacks.length; ++i) {
            if (this.backpackItemStacks[i] == null) continue;
            CompoundTag nbttagcompound1 = new CompoundTag();
            nbttagcompound1.putByte("Slot", (byte)i);
            this.backpackItemStacks[i].writeToNBT(nbttagcompound1);
            nbttaglist.addTag(nbttagcompound1);
        }
        nbttagcompound.put("Items", nbttaglist);
    }
    @Override
    public boolean stillValid(Player entityPlayer) {
        if (!BetterWithBackpacks.ENABLE_BACKPACKS){
            return false;
        }
        if (entityPlayer.getHeldItem() == null){
            return false;
        }
        ItemStack heldItem = entityPlayer.getHeldItem();
        if (
            heldItem.getItem() == stack.getItem() &&
            heldItem.getMetadata() == stack.getMetadata() &&
            heldItem.stackSize == stack.stackSize)
        {
            return true;
        }
        return false;
    }

    @Override
    public void sortContainer() {
        InventorySorter.sortInventory(backpackItemStacks);
    }
}
