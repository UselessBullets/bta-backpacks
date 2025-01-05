package tosutosu.betterwithbackpacks;

import net.minecraft.core.item.Item;
import tosutosu.betterwithbackpacks.item.ItemBackpack;
import turniplabs.halplibe.helper.ItemBuilder;

import static tosutosu.betterwithbackpacks.BetterWithBackpacks.MOD_ID;
import static tosutosu.betterwithbackpacks.BetterWithBackpacks.itemID;

public class ModItems {
    public static final Item leatherBackpack = new ItemBuilder(MOD_ID)
        .setIcon("betterwithbackpacks:item/leather_backpack")
        .build(new ItemBackpack("backpack.leather", MOD_ID + ":item/backpack_leather", itemID++, 9));
    public static final Item ironBackpack = new ItemBuilder(MOD_ID)
        .setIcon("betterwithbackpacks:item/iron_backpack")
        .build(new ItemBackpack("backpack.iron", MOD_ID + ":item/backpack_iron", itemID++, 15));
    public static final Item goldBackpack = new ItemBuilder(MOD_ID)
        .setIcon("betterwithbackpacks:item/gold_backpack")
        .build(new ItemBackpack("backpack.gold", MOD_ID + ":item/backpack_gold", itemID++, 21));
    public static final Item diamondBackpack = new ItemBuilder(MOD_ID)
        .setIcon("betterwithbackpacks:item/diamond_backpack")
        .build(new ItemBackpack("backpack.diamond", MOD_ID + ":item/backpack_diamond", itemID++, 27));

    public static void init(){}
}
