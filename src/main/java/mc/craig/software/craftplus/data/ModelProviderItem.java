package mc.craig.software.craftplus.data;

import mc.craig.software.craftplus.MinecraftPlus;
import mc.craig.software.craftplus.common.ModItems;
import mc.craig.software.craftplus.common.items.ParagliderItem;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModelProviderItem extends ItemModelProvider {

    public ModelProviderItem(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, MinecraftPlus.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (RegistryObject<Item> entry : ModItems.ITEMS.getEntries()) {
            if (entry.get() instanceof ForgeSpawnEggItem) continue;

            if (entry.get() instanceof ParagliderItem) {
                basicItem(new ResourceLocation(MinecraftPlus.MODID, ForgeRegistries.ITEMS.getKey(entry.get()).getPath() + "_copper_mod"));
                continue;
            }

            if (entry.get() instanceof ArmorItem) {
                layeredItem(ForgeRegistries.ITEMS.getKey(entry.get()), new ResourceLocation(MinecraftPlus.MODID, ForgeRegistries.ITEMS.getKey(entry.get()).getPath() + "_overlay"));
                continue;
            }

            basicItem(entry.get());
        }
    }

    public ItemModelBuilder layeredItem(ResourceLocation item, ResourceLocation resourceLocation)
    {
        return getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(item.getNamespace(), "item/" + item.getPath()))
                .texture("layer1",  new ResourceLocation(resourceLocation.getNamespace(), "item/" + resourceLocation.getPath()));
    }
}
