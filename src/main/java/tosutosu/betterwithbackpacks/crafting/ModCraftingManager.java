package tosutosu.betterwithbackpacks.crafting;


import tosutosu.betterwithbackpacks.BetterWithBackpacks;
import tosutosu.betterwithbackpacks.item.ItemBackpack;
import net.minecraft.core.block.Block;
import net.minecraft.core.crafting.CraftingManager;
import net.minecraft.core.item.Item;
import turniplabs.halplibe.helper.RecipeHelper;

public abstract class ModCraftingManager {

    public static void register() {
        //add recipes
        RecipeHelper.Crafting.createRecipe(ItemBackpack.LeatherBackpack, 1, new Object[]{
                "LLL",
                "LCL",
                "LLL",
                'L', Item.leather,
                'C', Block.chestPlanksOak});
        RecipeHelper.Crafting.createRecipe(ItemBackpack.IronBackpack, 1, new Object[]{
                "III",
                "IBI",
                "III",
                'I', Item.ingotIron,
                'B', ItemBackpack.LeatherBackpack});
        RecipeHelper.Crafting.createRecipe(ItemBackpack.GoldBackpack, 1, new Object[]{
                "III",
                "IBI",
                "III",
                'I', Item.ingotGold,
                'B', ItemBackpack.IronBackpack});
        RecipeHelper.Crafting.createRecipe(ItemBackpack.DiamondBackpack, 1, new Object[]{
                "III",
                "IBI",
                "III",
                'I', Item.diamond,
                'B', ItemBackpack.GoldBackpack});

        BetterWithBackpacks.LOGGER.info("Better with Backpacks! recipes loaded successfully."); //put recipes before this point
    }


    private static void addAlternatives(int index, Block[] alternatives) {
        Block[] current = CraftingManager.blockAlternatives[index];
        Block[] newAlts = new Block[current.length+alternatives.length];
        System.arraycopy(current, 0, newAlts, 0, current.length);
        System.arraycopy(alternatives, 0, newAlts, current.length, alternatives.length);
        CraftingManager.blockAlternatives[index] = newAlts;
    }

    private static void appendAlternatives(Block[] alternatives) {
        Block[][] current = CraftingManager.blockAlternatives;
        Block[][] newAlts = new Block[current.length+1][];
        System.arraycopy(current, 0, newAlts, 0, current.length);
        newAlts[newAlts.length-1] = alternatives;
        CraftingManager.blockAlternatives = newAlts;
    }
}