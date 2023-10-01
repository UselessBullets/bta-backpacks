package tosutosu.betterwithbackpacks.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.net.packet.Packet100OpenWindow;
import net.minecraft.server.entity.player.EntityPlayerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import tosutosu.betterwithbackpacks.BetterWithBackpacks;
import tosutosu.betterwithbackpacks.IPlayerDisplay;
import tosutosu.betterwithbackpacks.gui.container.ContainerBackpack;

@Mixin(value = EntityPlayerMP.class, remap = false)
public class EntityPlayerMPMixin implements IPlayerDisplay {
    @Unique
    private final Minecraft mc = Minecraft.getMinecraft(this);
    @Unique
    private final EntityPlayerMP thisAs = (EntityPlayerMP)(Object)this;
    @Shadow
    private void getNextWindowId() {}
    @Shadow
    private int currentWindowId = 0;
    @Override
    public void displayGUIBackpack(ItemStack stack) {
        getNextWindowId();
        ContainerBackpack backpack = new ContainerBackpack(thisAs.inventory, stack);
        thisAs.playerNetServerHandler.sendPacket(new Packet100OpenWindow(currentWindowId, BetterWithBackpacks.GUI_BACKPACK_ID, "Backpack", backpack.backpackInventory.getSizeInventory()));
        thisAs.craftingInventory = backpack;
        thisAs.craftingInventory.windowId = currentWindowId;
        thisAs.craftingInventory.onContainerInit(thisAs);
    }
}
