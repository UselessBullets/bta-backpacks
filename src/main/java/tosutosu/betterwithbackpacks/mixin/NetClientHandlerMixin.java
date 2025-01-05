package tosutosu.betterwithbackpacks.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.net.handler.PacketHandlerClient;
import net.minecraft.core.net.packet.PacketContainerOpen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tosutosu.betterwithbackpacks.BetterWithBackpacks;
import tosutosu.betterwithbackpacks.IPlayerDisplay;

@Mixin(value= PacketHandlerClient.class,remap = false)
public abstract class NetClientHandlerMixin {
    @Final
    @Shadow
    private Minecraft mc;

    @Inject(method="handleOpenWindow", at=@At("TAIL"))
    public void handleOpenWindow_injection(PacketContainerOpen packet, CallbackInfo ci) {
        if (packet.inventoryType == BetterWithBackpacks.GUI_BACKPACK_ID) {
            ((IPlayerDisplay) (mc.thePlayer)).displayGUIBackpack(mc.thePlayer.getHeldItem());
            mc.thePlayer.craftingInventory.containerId = packet.windowId;
        }
    }
}
