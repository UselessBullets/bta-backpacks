package tosutosu.betterwithbackpacks;

import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import com.mojang.nbt.NbtIo;
import com.mojang.nbt.tags.CompoundTag;
import net.minecraft.client.Minecraft;
import net.minecraft.core.net.packet.PacketCustomPayload;

public class BackpacksClient {
    public static void sendStackUpdate(final CompoundTag tag){
        final Minecraft mc = Minecraft.getMinecraft();
        if (!mc.currentWorld.isClientSide) {return;}
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            NbtIo.writeCompressed(tag, bos);
        } catch (final IOException e) {
            return;
        }
        final byte[] buffer = bos.toByteArray();
        mc.getSendQueue().addToSendQueue(new PacketCustomPayload("backpacks$setItems", buffer));
    }

    public static CompoundTag getStackUpdate(final byte[] data, final int len){
        CompoundTag tag = null;
        try {
            tag = NbtIo.readCompressed(new ByteArrayInputStream(data));
        } catch (final IOException e) {
            return null;
        }
        return tag;
    }
}
