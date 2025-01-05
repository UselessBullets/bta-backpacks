package tosutosu.betterwithbackpacks.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.PlayerLocal;
import net.minecraft.core.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import tosutosu.betterwithbackpacks.IPlayerDisplay;
import tosutosu.betterwithbackpacks.gui.guiscreen.GuiBackpack;

@Mixin(value = PlayerLocal.class, remap = false)
public class PlayerLocalMixin implements IPlayerDisplay {
    @Unique
    private final Minecraft mc = Minecraft.getMinecraft();
    @Unique
    private final PlayerLocal thisAs = (PlayerLocal)(Object)this;

    @Override
    public void bta_backpacks$displayGUIBackpack(final ItemStack stack) {
        this.mc.displayScreen(new GuiBackpack(this.thisAs, stack));
    }
}

