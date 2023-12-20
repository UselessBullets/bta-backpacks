package tosutosu.betterwithbackpacks;

import com.mojang.nbt.CompoundTag;
import net.minecraft.client.Minecraft;
import net.minecraft.core.net.packet.Packet134ItemData;

public class BackpacksClient {
    public static void sendStackUpdate(CompoundTag tag){
        Minecraft mc = Minecraft.getMinecraft(Minecraft.class);
        mc.getSendQueue().addToSendQueue(new Packet134ItemData(mc.thePlayer.inventory.currentItem, tag));
    }
}
