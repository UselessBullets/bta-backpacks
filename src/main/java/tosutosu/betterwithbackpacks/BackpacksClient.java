package tosutosu.betterwithbackpacks;

import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import com.mojang.nbt.NbtIo;
import com.mojang.nbt.tags.CompoundTag;
import net.minecraft.client.Minecraft;
import net.minecraft.core.net.packet.PacketCustomPayload;

public class BackpacksClient {
    public static void sendStackUpdate(CompoundTag tag){
        Minecraft mc = Minecraft.getMinecraft();
        if (!mc.currentWorld.isClientSide) {return;}
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            NbtIo.writeCompressed(tag, bos);
        } catch (IOException e) {
            return;
        }
        byte[] buffer = bos.toByteArray();
        mc.getSendQueue().addToSendQueue(new PacketCustomPayload("backpacks$setItems", buffer));
    }

    public static CompoundTag getStackUpdate(byte[] data, int len){
        CompoundTag tag = null;
        try {
            tag = NbtIo.readCompressed(new ByteArrayInputStream(data));
        } catch (IOException e) {
            return null;
        }
        return tag;
    }
}
