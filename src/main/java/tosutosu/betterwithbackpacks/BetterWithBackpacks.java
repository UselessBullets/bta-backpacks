package tosutosu.betterwithbackpacks;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tosutosu.betterwithbackpacks.crafting.ModCraftingManager;
import tosutosu.betterwithbackpacks.item.ModItems;
import turniplabs.halplibe.util.ConfigHandler;

import java.util.Properties;

public class BetterWithBackpacks implements ModInitializer {
    public static final String MOD_ID = "betterwithbackpacks";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static int GUI_LABEL_COLOR = 0x404040;

    private void handleConfig() {
        Properties prop = new Properties();
        prop.setProperty("starting_block_id","2137");
        prop.setProperty("starting_item_id","21370");
        ConfigHandler config = new ConfigHandler(MOD_ID,prop);
        UtilIdRegistrar.initIds(
                config.getInt("starting_block_id"),
                config.getInt("starting_item_id"));
        config.updateConfig();
    }

    @Override
    public void onInitialize() {
        LOGGER.info("Better with Backpacks! is loading...");
        handleConfig();

        UtilIdRegistrar.setIdToItem();
        ModItems.register();
        UtilIdRegistrar.setIdToBlock();
        ModCraftingManager.register();

        LOGGER.info("Better with Backpacks! initialized.");
    }
}