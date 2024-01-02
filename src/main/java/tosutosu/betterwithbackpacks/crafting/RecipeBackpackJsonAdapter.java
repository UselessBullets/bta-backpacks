package tosutosu.betterwithbackpacks.crafting;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import net.minecraft.core.block.Block;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.data.registry.recipe.adapter.RecipeJsonAdapter;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class RecipeBackpackJsonAdapter implements RecipeJsonAdapter<RecipeEntryBackpack> {
    @Override
    public RecipeEntryBackpack deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();
        List<String> pattern = obj.get("pattern").getAsJsonArray().asList().stream().map(JsonElement::getAsString).collect(Collectors.toList());
        List<RecipeSymbol> symbols = obj.get("symbols").getAsJsonArray().asList().stream().map(E -> (RecipeSymbol)context.deserialize(E, RecipeSymbol.class)).collect(Collectors.toList());
        ItemStack result = context.deserialize(obj.get("result").getAsJsonObject(), ItemStack.class);
        boolean consumeContainers = obj.get("consumeContainers").getAsBoolean();
        return parseRecipe(pattern, symbols, result, consumeContainers);
    }

    @Override
    public JsonElement serialize(RecipeEntryBackpack src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();
        obj.addProperty("name", src.toString());
        obj.addProperty("type", Registries.RECIPE_TYPES.getKey(src.getClass()));
        RecipeSymbol[] symbols = src.getInput();
        StringBuilder sb = new StringBuilder();
        for (RecipeSymbol recipeSymbol : symbols) {
            if (recipeSymbol == null) {
                sb.append(" ");
                continue;
            }
            if (recipeSymbol.getSymbol() == '\u0000') {
                sb.append(" ");
                continue;
            }
            sb.append(recipeSymbol.getSymbol());
        }
        String s = sb.toString();
        String[] pattern = s.split("(?<=\\G.{3})");
        JsonArray arr = new JsonArray();
        for (String string : pattern) {
            arr.add(string);
        }
        ArrayList<RecipeSymbol> arrayList = new ArrayList<RecipeSymbol>();
        for (RecipeSymbol symbol : symbols) {
            if (symbol == null) continue;
            boolean found = false;
            for (RecipeSymbol recipeSymbol : arrayList) {
                if (recipeSymbol.getSymbol() != symbol.getSymbol()) continue;
                found = true;
                break;
            }
            if (found) continue;
            arrayList.add(symbol);
        }
        obj.add("pattern", arr);
        obj.add("symbols", context.serialize(arrayList));
        obj.add("result", context.serialize(src.getOutput()));
        obj.addProperty("consumeContainers", src.consumeContainerItem);
        return obj;
    }
    public static RecipeEntryBackpack parseRecipe(List<String> pattern, List<RecipeSymbol> symbols, ItemStack result, boolean consumeContainerItem) {
        HashSet<Character> chars = new HashSet<Character>();
        for (String s : pattern) {
            char[] chars1 = s.toCharArray();
            for (char c : chars1) {
                chars.add(c);
            }
        }
        ArrayList<Object> objs = new ArrayList<Object>();
        block2: for (Character c : chars) {
            for (RecipeSymbol symbol : symbols) {
                if (symbol.getSymbol() != c) continue;
                objs.add(c);
                objs.add(symbol);
                continue block2;
            }
        }
        objs.add(0, pattern.toArray(new String[0]));
        return parseRecipe(result, consumeContainerItem, objs.toArray());
    }
    public static RecipeEntryBackpack parseRecipe(ItemStack itemstack, boolean consumeContainerItem, Object ... aobj) {
        StringBuilder s = new StringBuilder();
        int i = 0;
        int j = 0;
        int k = 0;
        if (aobj[i] instanceof String[]) {
            for (String s2 : (String[])aobj[i++]) {
                ++k;
                j = s2.length();
                s.append(s2);
            }
        } else {
            while (aobj[i] instanceof String) {
                String s1 = (String)aobj[i++];
                ++k;
                j = s1.length();
                s = new StringBuilder(s + s1);
            }
        }
        HashMap<Character, RecipeSymbol> map = new HashMap<Character, RecipeSymbol>();
        while (i < aobj.length) {
            Character character = (Character)aobj[i];
            RecipeSymbol recipeSymbol = null;
            if (aobj[i + 1] instanceof Item) {
                recipeSymbol = new RecipeSymbol(character, new ItemStack((Item)aobj[i + 1]), null);
            } else if (aobj[i + 1] instanceof Block) {
                recipeSymbol = new RecipeSymbol(character, new ItemStack((Block)aobj[i + 1]), null);
            } else if (aobj[i + 1] instanceof ItemStack) {
                recipeSymbol = new RecipeSymbol(character, (ItemStack)aobj[i + 1], null);
            }
            if (aobj[i + 1] instanceof String) {
                recipeSymbol = new RecipeSymbol(character, null, (String)aobj[i + 1]);
            }
            if (aobj[i + 1] instanceof RecipeSymbol) {
                recipeSymbol = (RecipeSymbol)aobj[i + 1];
            }
            map.put(character, recipeSymbol);
            i += 2;
        }
        RecipeSymbol[] symbols = new RecipeSymbol[j * k];
        for (int i1 = 0; i1 < j * k; ++i1) {
            char c = s.charAt(i1);
            symbols[i1] = map.containsKey(c) ? map.get(c).copy() : null;
        }
        return new RecipeEntryBackpack(j, k, symbols, itemstack, consumeContainerItem);
    }
}
