package tosutosu.betterwithbackpacks;

import net.minecraft.core.item.Item;
import tosutosu.betterwithbackpacks.item.ItemBackpack;
import turniplabs.halplibe.helper.ItemHelper;

import static tosutosu.betterwithbackpacks.BetterWithBackpacks.itemID;

public class ModItems {
    public static final Item LeatherBackpack = ItemHelper.createItem(BetterWithBackpacks.MOD_ID, new ItemBackpack("backpack.leather", itemID++,9), "backpack.leather", "leather_backpack.png").setMaxStackSize(1);
    public static final Item IronBackpack = ItemHelper.createItem(BetterWithBackpacks.MOD_ID, new ItemBackpack("backpack.iron",itemID++,15), "backpack.iron", "iron_backpack.png").setMaxStackSize(1);
    public static final Item GoldBackpack = ItemHelper.createItem(BetterWithBackpacks.MOD_ID, new ItemBackpack("backpack.gold",itemID++,21), "backpack.gold", "gold_backpack.png").setMaxStackSize(1);
    public static final Item DiamondBackpack = ItemHelper.createItem(BetterWithBackpacks.MOD_ID, new ItemBackpack("backpack.diamond",itemID++,27), "backpack.diamond", "diamond_backpack.png").setMaxStackSize(1);
    public static void init(){}
}
