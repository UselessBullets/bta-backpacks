package tosutosu.betterwithbackpacks;

import net.minecraft.core.item.Item;
import tosutosu.betterwithbackpacks.item.ItemBackpack;
import turniplabs.halplibe.helper.ItemBuilder;

import static tosutosu.betterwithbackpacks.BetterWithBackpacks.itemID;

public class ModItems {
    public static final Item leatherBackpack = new ItemBuilder(BetterWithBackpacks.MOD_ID)
        .setIcon("betterwithbackpacks:item/leather_backpack")
        .build(new ItemBackpack("backpack.leather", itemID++, 9));
    public static final Item ironBackpack = new ItemBuilder(BetterWithBackpacks.MOD_ID)
        .setIcon("betterwithbackpacks:item/iron_backpack")
        .build(new ItemBackpack("backpack.iron", itemID++, 15));
    public static final Item goldBackpack = new ItemBuilder(BetterWithBackpacks.MOD_ID)
        .setIcon("betterwithbackpacks:item/gold_backpack")
        .build(new ItemBackpack("backpack.gold", itemID++, 21));
    public static final Item diamondBackpack = new ItemBuilder(BetterWithBackpacks.MOD_ID)
        .setIcon("betterwithbackpacks:item/diamond_backpack")
        .build(new ItemBackpack("backpack.diamond", itemID++, 27));

    public static void init(){}
}
