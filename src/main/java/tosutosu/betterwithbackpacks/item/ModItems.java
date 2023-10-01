package tosutosu.betterwithbackpacks.item;

import tosutosu.betterwithbackpacks.BetterWithBackpacks;
import tosutosu.betterwithbackpacks.UtilIdRegistrar;
import net.minecraft.core.item.Item;
import turniplabs.halplibe.helper.ItemHelper;

public class ModItems {
    public static final Item LeatherBackpack = ItemHelper.createItem(BetterWithBackpacks.MOD_ID, new ItemBackpack("leather.backpack",UtilIdRegistrar.nextId(),9), "leather.backpack", "leather_backpack.png").setMaxStackSize(1);
    public static final Item IronBackpack = ItemHelper.createItem(BetterWithBackpacks.MOD_ID, new ItemBackpack("iron.backpack",UtilIdRegistrar.nextId(),15), "iron.backpack", "iron_backpack.png").setMaxStackSize(1);
    public static final Item GoldBackpack = ItemHelper.createItem(BetterWithBackpacks.MOD_ID, new ItemBackpack("gold.backpack",UtilIdRegistrar.nextId(),21), "gold.backpack", "gold_backpack.png").setMaxStackSize(1);
    public static final Item DiamondBackpack = ItemHelper.createItem(BetterWithBackpacks.MOD_ID, new ItemBackpack("diamond.backpack",UtilIdRegistrar.nextId(),27), "diamond.backpack", "diamond_backpack.png").setMaxStackSize(1);
    public static void register() {
    }
}