package gorgi.morestuff.item;

import gorgi.morestuff.MoreStuff;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item RAW_COBALT = registerItem("raw_cobalt", new Item(new Item.Settings()));
    public static final Item COBALT = registerItem("cobalt", new Item(new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(MoreStuff.MOD_ID, name), item);
    }

    public static void registerModItems() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(
                entries -> {
                    entries.addAfter(Items.GOLD_INGOT, COBALT);
                    entries.addAfter(Items.RAW_GOLD, RAW_COBALT);
                });
    }
}
