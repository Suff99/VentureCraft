package mc.craig.software.craftplus.data;

import mc.craig.software.craftplus.MinecraftPlus;
import mc.craig.software.craftplus.common.ModItems;
import mc.craig.software.craftplus.util.ModTags;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class ItemTagsProvider extends TagsProvider<Item> {

    public ItemTagsProvider(DataGenerator dataGenerator, Registry<Item> itemRegistry, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, itemRegistry, MinecraftPlus.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {

        for (Map.Entry<ResourceKey<Item>, Item> entry : ForgeRegistries.ITEMS.getEntries()) {
            Item item = entry.getValue();
            if(ForgeRegistries.ITEMS.getKey(item).getPath().contains("seed")){
                add(ModTags.OWL_FOOD, item);
            }
        }

        add(ModTags.OWL_FOOD, Items.RABBIT, Items.COOKED_RABBIT, Items.RABBIT_FOOT, Items.RABBIT_HIDE, Items.TURTLE_EGG, Items.SPIDER_EYE, Items.CHICKEN, Items.COOKED_CHICKEN);
        add(ModTags.OWL_FOOD, ItemTags.FISHES);

        add(ModTags.BIRD_POISON, Items.COOKIE, Items.CAKE);
        add(ModTags.TOTEMS, ModItems.TOTEM.get());

    }

    public void add(TagKey<Item> branch, Item item) {
        this.tag(branch).add(item);
    }

    public void add(TagKey<Item> branch, TagKey<Item>... item) {
        this.tag(branch).addTags(item);
    }

    public void add(TagKey<Item> branch, Item... item) {
        this.tag(branch).add(item);
    }
}
