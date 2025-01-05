package tosutosu.betterwithbackpacks.mixin;

import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import tosutosu.betterwithbackpacks.IPlayerDisplay;

@Mixin(value = Player.class, remap = false)
public class EntityPlayerMixin implements IPlayerDisplay {
    @Override
    public void displayGUIBackpack(ItemStack stack) {

    }
}
