package gorgi.morestuff.datagen.lang;

import gorgi.morestuff.block.ModBlocks;
import gorgi.morestuff.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModEnglishLangProvider extends FabricLanguageProvider {
    public ModEnglishLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItems.RAW_COBALT, "Raw Cobalt");
        translationBuilder.add(ModItems.COBALT, "Cobalt");

        translationBuilder.add(ModBlocks.RAW_COBALT_BLOCK, "Block of Raw Cobalt");
        translationBuilder.add(ModBlocks.COBALT_BLOCK, "Block of Cobalt");
        translationBuilder.add(ModBlocks.COBALT_ORE, "Cobalt Ore");
        translationBuilder.add(ModBlocks.DEEPSLATE_COBALT_ORE, "Deepslate Cobalt Ore");

        translationBuilder.add(ModItems.METAL_DETECTOR, "Metal Detector");
    }
}
