package tosutosu.betterwithbackpacks.crafting;

import com.mojang.nbt.tags.CompoundTag;
import net.minecraft.core.data.registry.recipe.HasJsonAdapter;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.data.registry.recipe.adapter.RecipeJsonAdapter;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryCraftingShaped;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.container.ContainerCrafting;
import net.minecraft.core.player.inventory.menu.MenuCrafting;
import tosutosu.betterwithbackpacks.item.ItemBackpack;

public class RecipeEntryBackpack extends RecipeEntryCraftingShaped
        implements HasJsonAdapter {

    public RecipeEntryBackpack(int recipeWidth, int recipeHeight, RecipeSymbol[] input, ItemStack output) {
        super(recipeWidth, recipeHeight, input, output);
    }

    public RecipeEntryBackpack(int recipeWidth, int recipeHeight, RecipeSymbol[] input, ItemStack output, boolean consumeContainerItem) {
        super(recipeWidth, recipeHeight, input, output, consumeContainerItem);
    }

    public RecipeEntryBackpack() {
        super();
    }

    @Override
    public ItemStack getCraftingResult(ContainerCrafting inventorycrafting) {
        if (getOutput() == null){
            return null;
        }
        ItemStack returnStack = new ItemStack(this.getOutput());
        CompoundTag backpackTag = returnStack.getData();
        for (int i = 0; i < inventorycrafting.getContainerSize(); ++i) {
            ItemStack itemstack1 = inventorycrafting.getItem(i);
            if (itemstack1 == null) continue;
            if (itemstack1.getItem() instanceof ItemBackpack){
                backpackTag = itemstack1.getData();
            }
        }
        returnStack.setData(backpackTag);
        return returnStack;
    }

    @Override
    public RecipeJsonAdapter<?> getAdapter() {
        return new RecipeBackpackJsonAdapter();
    }
}
