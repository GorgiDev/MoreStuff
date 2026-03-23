package gorgi.morestuff.block;

import gorgi.morestuff.MoreStuff;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModBlocks {
    public static final Block RAW_COBALT_BLOCK = registerBlock("raw_cobalt_block",
            new Block(AbstractBlock.Settings.create().strength(5f, 6f).requiresTool()
                    .sounds(BlockSoundGroup.STONE).mapColor(MapColor.DARK_AQUA)
                    .instrument(NoteBlockInstrument.BASEDRUM)));

    public static final Block COBALT_BLOCK = registerBlock("cobalt_block",
            new Block(AbstractBlock.Settings.create().strength(5f, 6f).requiresTool()
                    .sounds(BlockSoundGroup.METAL).mapColor(MapColor.TERRACOTTA_BLUE)
                    .instrument(NoteBlockInstrument.IRON_XYLOPHONE)));

    public static final Block COBALT_ORE = registerBlock("cobalt_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(2, 6),
                    AbstractBlock.Settings.create().strength(3f, 3f).requiresTool()
                            .sounds(BlockSoundGroup.STONE).mapColor(MapColor.STONE_GRAY)
                            .instrument(NoteBlockInstrument.BASEDRUM)));

    public static final Block DEEPSLATE_COBALT_ORE = registerBlock("deepslate_cobalt_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(2, 6),
                    AbstractBlock.Settings.create().strength(3.5f, 3f)
                            .requiresTool().sounds(BlockSoundGroup.DEEPSLATE).mapColor(MapColor.DEEPSLATE_GRAY)
                            .instrument(NoteBlockInstrument.BASEDRUM)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(MoreStuff.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(MoreStuff.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.addAfter(Blocks.DEEPSLATE_LAPIS_ORE, COBALT_ORE);
            entries.addAfter(COBALT_ORE, DEEPSLATE_COBALT_ORE);
            entries.addAfter(Blocks.RAW_GOLD_BLOCK, RAW_COBALT_BLOCK);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.addAfter(Blocks.LAPIS_BLOCK, COBALT_BLOCK);
        });
    }
}
