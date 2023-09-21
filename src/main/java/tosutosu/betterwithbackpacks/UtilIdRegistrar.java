package tosutosu.betterwithbackpacks;

public class UtilIdRegistrar {
    private static int block_id;
    private static int item_id;
    private static int curr_id = 0;

    public static void initIds(int blockId, int itemId) {
        block_id = blockId;
        item_id = itemId;
    }

    public static int nextId() {
        return curr_id++;
    }
    public static void setIdToBlock() {curr_id = block_id;}
    public static void setIdToItem() {curr_id = item_id;}
}