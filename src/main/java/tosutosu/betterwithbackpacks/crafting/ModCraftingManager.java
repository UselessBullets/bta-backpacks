package tosutosu.betterwithbackpacks.crafting;


import tosutosu.betterwithbackpacks.BetterWithBackpacks;
import tosutosu.betterwithbackpacks.item.ItemBackpack;
import net.minecraft.core.block.Block;
import net.minecraft.core.item.Item;
import tosutosu.betterwithbackpacks.item.ModItems;
import turniplabs.halplibe.helper.RecipeHelper;

public abstract class ModCraftingManager {

    public static void register() {
        //add recipes
        RecipeHelper.Crafting.createRecipe(ModItems.LeatherBackpack, 1, new Object[]{
                "LLL",
                "LCL",
                "LLL",
                'L', Item.leather,
                'C', Block.chestPlanksOak});
        RecipeHelper.Crafting.createRecipe(ModItems.IronBackpack, 1, new Object[]{
                "III",
                "IBI",
                "III",
                'I', Item.ingotIron,
                'B', ModItems.LeatherBackpack});
        RecipeHelper.Crafting.createRecipe(ModItems.GoldBackpack, 1, new Object[]{
                "III",
                "IBI",
                "III",
                'I', Item.ingotGold,
                'B', ModItems.IronBackpack});
        RecipeHelper.Crafting.createRecipe(ModItems.DiamondBackpack, 1, new Object[]{
                "III",
                "IBI",
                "III",
                'I', Item.diamond,
                'B', ModItems.GoldBackpack});

        BetterWithBackpacks.LOGGER.info("Better with Backpacks! recipes loaded successfully."); //put recipes before this point
    }
}