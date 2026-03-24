package gorgi.morestuff.datagen;

import gorgi.morestuff.datagen.lang.ModEnglishLangProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class ModLanguageProviders {
    public static void register(FabricDataGenerator.Pack pack) {
        pack.addProvider(ModEnglishLangProvider::new);
    }
}
