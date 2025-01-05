package tosutosu.betterwithbackpacks.gui.container;

import net.minecraft.client.Minecraft;
import net.minecraft.core.InventoryAction;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.container.ContainerInventory;
import net.minecraft.core.player.inventory.menu.MenuAbstract;
import net.minecraft.core.player.inventory.slot.Slot;
import tosutosu.betterwithbackpacks.gui.slots.SlotBackpack;
import tosutosu.betterwithbackpacks.item.ItemBackpackInventory;
import tosutosu.betterwithbackpacks.BackpacksClient;

import java.util.List;

public class ContainerBackpack extends MenuAbstract {
    private ContainerInventory playerInv;
    private ItemStack backpack;
    public ItemBackpackInventory backpackInventory;

    public ContainerBackpack(ContainerInventory playerInv, ItemStack backpack){
        this.playerInv = playerInv;
        this.backpack = backpack;
        this.backpackInventory = new ItemBackpackInventory(backpack);
        int slotsNum = backpackInventory.getContainerSize();
        int rows = (int) Math.ceil(slotsNum/9d);
        for (int i = 0; i < rows; ++i) {
            int width = 9;
            if (i == rows-1){
                width = slotsNum - 9 * i;
            }
            for (int k = 0; k < width; ++k) {
                this.addSlot(new SlotBackpack(backpackInventory, k + i * 9, 8 + k * 18, 18 + 18 * i));
            }
        }
        for (int i = 0; i < 3; ++i) {
            for (int k = 0; k < 9; ++k) {
                this.addSlot(new Slot(playerInv, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
            }
        }
        for (int j = 0; j < 9; ++j) {
            this.addSlot(new Slot(playerInv, j, 8 + j * 18, 142));
        }
    }
    @Override
    public List<Integer> getMoveSlots(InventoryAction inventoryAction, Slot slot, int i, Player entityPlayer) {
        int chestSize = backpackInventory.getContainerSize();
        if (slot.index >= 0 && slot.index < chestSize) {
            return this.getSlots(0, chestSize, false);
        }
        if (inventoryAction == InventoryAction.MOVE_ALL) {
            if (slot.index >= chestSize && slot.index < chestSize + 27) {
                return this.getSlots(chestSize, 27, false);
            }
            if (slot.index >= chestSize + 27 && slot.index < chestSize + 36) {
                return this.getSlots(chestSize + 27, 9, false);
            }
        } else if (slot.index >= chestSize && slot.index < chestSize + 36) {
            return this.getSlots(chestSize, 36, false);
        }
        return null;
    }
    @Override
    public List<Integer> getTargetSlots(InventoryAction inventoryAction, Slot slot, int i, Player entityPlayer) {
        int chestSize = backpackInventory.getContainerSize();
        if (slot.index < chestSize) {
            return this.getSlots(chestSize, 36, true);
        }
        return this.getSlots(0, chestSize, false);
    }

    @Override
    public boolean stillValid(Player entityPlayer) {
        return backpackInventory.stillValid(entityPlayer);
    }

    @Override
    public ItemStack clicked(InventoryAction action, int[] args, Player player) {
        int slotId = backpackInventory.getContainerSize() + player.inventory.getCurrentItemIndex() - 9;
        if (player.inventory.getCurrentItemIndex() < 9) slotId = backpackInventory.getContainerSize() + player.inventory.getCurrentItemIndex() + (9 * 3);
        if (args != null && args.length >= 1 && args[0] == slotId) {
            if (player.world.isClientSide) {
                Minecraft.getMinecraft().thePlayer.closeScreen();
            }
            return player.getHeldItem();
        }
        ItemStack is = super.clicked(action, args, player);
        if (player.world.isClientSide) {
            backpackInventory.setChanged();
            BackpacksClient.sendStackUpdate(backpackInventory.stack.getData());
            if (player.getHeldItem() == null) {
                // Good bye
                Minecraft.getMinecraft().thePlayer.closeScreen();
                return is;
            }
            player.getHeldItem().setData(backpackInventory.stack.getData());
        }
        return is;
    }
}
