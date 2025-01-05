package tosutosu.betterwithbackpacks.mixin;

import net.minecraft.core.item.ItemStack;
import net.minecraft.core.net.packet.PacketContainerOpen;
import net.minecraft.server.entity.player.PlayerServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import tosutosu.betterwithbackpacks.BetterWithBackpacks;
import tosutosu.betterwithbackpacks.IPlayerDisplay;
import tosutosu.betterwithbackpacks.gui.container.ContainerBackpack;
import tosutosu.betterwithbackpacks.item.ItemBackpack;

@Mixin(value = PlayerServer.class, remap = false)
public class EntityPlayerMPMixin implements IPlayerDisplay {
    @Unique
    private final PlayerServer thisAs = (PlayerServer)(Object)this;
    @Shadow
    private void getNextWindowId() {}
    @Shadow
    private int currentWindowId = 0;
    @Override
    public void displayGUIBackpack(ItemStack stack) {
        if (stack != null && stack.getItem() instanceof ItemBackpack) {
            getNextWindowId();
            ContainerBackpack backpack = new ContainerBackpack(thisAs.inventory, stack);
            thisAs.playerNetServerHandler.sendPacket(new PacketContainerOpen(currentWindowId, BetterWithBackpacks.GUI_BACKPACK_ID, "Backpack", backpack.backpackInventory.getContainerSize()));
            thisAs.craftingInventory = backpack;
            thisAs.craftingInventory.containerId = currentWindowId;
            thisAs.craftingInventory.addSlotListener(thisAs);
        }
    }
}
