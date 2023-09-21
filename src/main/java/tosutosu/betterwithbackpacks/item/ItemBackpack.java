package tosutosu.betterwithbackpacks.item;

import tosutosu.betterwithbackpacks.BetterWithBackpacks;
import tosutosu.betterwithbackpacks.UtilIdRegistrar;
import net.minecraft.core.item.Item;
import turniplabs.halplibe.helper.ItemHelper;

public class ItemBackpack {
    public static final Item LeatherBackpack = ItemHelper.createItem(BetterWithBackpacks.MOD_ID, new Item(UtilIdRegistrar.nextId()), "leather.backpack", "leather_backpack.png").setMaxStackSize(1);
    public static final Item IronBackpack = ItemHelper.createItem(BetterWithBackpacks.MOD_ID, new Item(UtilIdRegistrar.nextId()), "iron.backpack", "iron_backpack.png").setMaxStackSize(1);
    public static final Item GoldBackpack = ItemHelper.createItem(BetterWithBackpacks.MOD_ID, new Item(UtilIdRegistrar.nextId()), "gold.backpack", "gold_backpack.png").setMaxStackSize(1);
    public static final Item DiamondBackpack = ItemHelper.createItem(BetterWithBackpacks.MOD_ID, new Item(UtilIdRegistrar.nextId()), "diamond.backpack", "diamond_backpack.png").setMaxStackSize(1);
    public static void register() {
    }
}