package tosutosu.betterwithbackpacks.mixin;

import com.mojang.nbt.CompoundTag;
import net.minecraft.server.net.handler.NetServerHandler;
import net.minecraft.server.entity.player.EntityPlayerMP;
import net.minecraft.core.net.packet.Packet250CustomPayload;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tosutosu.betterwithbackpacks.BetterWithBackpacks;
import tosutosu.betterwithbackpacks.BackpacksClient;

@Mixin(value= NetServerHandler.class,remap = false)
public abstract class NetServerHandlerMixin {
    @Shadow
    private EntityPlayerMP playerEntity;

    @Inject(method="handleCustomPayload",at=@At("HEAD"),cancellable=true)
    public void inject(Packet250CustomPayload packet, CallbackInfo ci) {
        if ("backpacks$setItems".equals(packet.channel)) {
            ci.cancel();
            int len = packet.getPacketSize() - (packet.channel.length() + 4);
            CompoundTag tag = BackpacksClient.getStackUpdate(packet.data, len);
            if (playerEntity.inventory.getStackInSlot(playerEntity.inventory.currentItem) != null && tag != null) {
                playerEntity.inventory.getStackInSlot(playerEntity.inventory.currentItem).setData(tag);
            }
        }
    }
}
