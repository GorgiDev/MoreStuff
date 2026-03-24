package gorgi.morestuff.datagen;

import gorgi.morestuff.block.ModBlocks;
import gorgi.morestuff.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        List<ItemConvertible> COBALT_SMELTABLES = List.of(ModItems.RAW_COBALT,
                ModBlocks.COBALT_ORE, ModBlocks.DEEPSLATE_COBALT_ORE);

        offerSmelting(exporter, COBALT_SMELTABLES, RecipeCategory.MISC, ModItems.COBALT, 0.5f, 180, "cobalt");
        offerBlasting(exporter, COBALT_SMELTABLES, RecipeCategory.MISC, ModItems.COBALT, 0.5f, 90, "cobalt");
        offerSmelting(exporter, List.of(ModBlocks.RAW_COBALT_BLOCK), RecipeCategory.MISC, ModBlocks.COBALT_BLOCK, 0.5f, 200, "cobalt_block");
        offerBlasting(exporter, List.of(ModBlocks.RAW_COBALT_BLOCK), RecipeCategory.MISC, ModBlocks.COBALT_BLOCK, 0.5f, 200, "cobalt_block");

        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.COBALT,
                RecipeCategory.DECORATIONS, ModBlocks.COBALT_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.RAW_COBALT,
                RecipeCategory.DECORATIONS, ModBlocks.RAW_COBALT_BLOCK);
    }
}
