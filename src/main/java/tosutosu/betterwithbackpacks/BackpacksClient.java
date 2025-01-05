package tosutosu.betterwithbackpacks;

import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.util.Arrays;
import com.mojang.nbt.CompoundTag;
import com.mojang.nbt.NbtIo;
import net.minecraft.client.Minecraft;
import net.minecraft.core.net.packet.Packet250CustomPayload;

public class BackpacksClient {
    public static void sendStackUpdate(CompoundTag tag){
        Minecraft mc = Minecraft.getMinecraft(Minecraft.class);
        if (!mc.theWorld.isClientSide) {return;}
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            NbtIo.writeCompressed(tag, bos);
        } catch (IOException e) {
            return;
        }
        byte[] buffer = bos.toByteArray();
        mc.getSendQueue().addToSendQueue(new Packet250CustomPayload("backpacks$setItems", buffer));
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
