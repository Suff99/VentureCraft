package mc.craig.software.craftplus.data.forge;

import mc.craig.software.craftplus.common.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class RecipeProvider extends net.minecraft.data.recipes.RecipeProvider {
    public RecipeProvider(DataGenerator p_125973_) {
        super(p_125973_);
    }

    @Override
    protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> consumer) {

        // Inforced Paper
        ShapedRecipeBuilder.shaped(ModItems.REINFORCED_PAPER.get()).pattern("LPL").pattern("PLP").pattern("LPL").define('L', Items.LEATHER).define('P', Items.PAPER).group("gliders").unlockedBy("has_crafting_table", has(Blocks.CRAFTING_TABLE)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.REINFORCED_PAPER_GOLD.get()).pattern("GGG").pattern("GPG").pattern("GGG").define('G', Items.GOLD_INGOT).define('P', ModItems.REINFORCED_PAPER.get()).group("gliders").unlockedBy("has_crafting_table", has(Blocks.CRAFTING_TABLE)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.REINFORCED_PAPER_DIAMOND.get()).pattern("DDD").pattern("DPD").pattern("DDD").define('D', Items.DIAMOND).define('P', ModItems.REINFORCED_PAPER.get()).group("gliders").unlockedBy("has_crafting_table", has(Blocks.CRAFTING_TABLE)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.REINFORCED_PAPER_NETHERITE.get()).pattern("NNN").pattern("NPN").pattern("NNN").define('N', Items.NETHERITE_SCRAP).define('P', ModItems.REINFORCED_PAPER.get()).group("gliders").unlockedBy("has_crafting_table", has(Blocks.CRAFTING_TABLE)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.REINFORCED_PAPER_IRON.get()).pattern("III").pattern("IPI").pattern("III").define('I', Items.IRON_INGOT).define('P', ModItems.REINFORCED_PAPER.get()).group("gliders").unlockedBy("has_crafting_table", has(Blocks.CRAFTING_TABLE)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.REINFORCED_PAPER_SAPPHIRE.get()).pattern("III").pattern("IPI").pattern("III").define('I', ModItems.SAPPHIRE_GEM.get()).define('P', ModItems.REINFORCED_PAPER.get()).group("gliders").unlockedBy("has_crafting_table", has(Blocks.CRAFTING_TABLE)).save(consumer);

        // Gliders
        ShapedRecipeBuilder.shaped(ModItems.PARAGLIDER_WOOD.get()).pattern("RRR").pattern("SWS").pattern("WOW").define('O', ModItems.OWL_FEATHER.get()).define('R', ModItems.REINFORCED_PAPER.get()).define('W', Items.STICK).define('S', Items.STRING).group("gliders").unlockedBy("has_crafting_table", has(Blocks.CRAFTING_TABLE)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.PARAGLIDER_IRON.get()).pattern("RRR").pattern("SWS").pattern("WOW").define('O', ModItems.OWL_FEATHER.get()).define('R', ModItems.REINFORCED_PAPER_IRON.get()).define('W', Items.STICK).define('S', Items.STRING).group("gliders").unlockedBy("has_crafting_table", has(Blocks.CRAFTING_TABLE)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.PARAGLIDER_GOLD.get()).pattern("RRR").pattern("SWS").pattern("WOW").define('O', ModItems.OWL_FEATHER.get()).define('R', ModItems.REINFORCED_PAPER_GOLD.get()).define('W', Items.STICK).define('S', Items.STRING).group("gliders").unlockedBy("has_crafting_table", has(Blocks.CRAFTING_TABLE)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.PARAGLIDER_DIAMOND.get()).pattern("RRR").pattern("SWS").pattern("WOW").define('O', ModItems.OWL_FEATHER.get()).define('R', ModItems.REINFORCED_PAPER_DIAMOND.get()).define('W', Items.STICK).define('S', Items.STRING).group("gliders").unlockedBy("has_crafting_table", has(Blocks.CRAFTING_TABLE)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.PARAGLIDER_NETHERITE.get()).pattern("RRR").pattern("SWS").pattern("WOW").define('O', ModItems.OWL_FEATHER.get()).define('R', ModItems.REINFORCED_PAPER_NETHERITE.get()).define('W', Items.STICK).define('S', Items.STRING).group("gliders").unlockedBy("has_crafting_table", has(Blocks.CRAFTING_TABLE)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.PARAGLIDER_SAPPHIRE.get()).pattern("RRR").pattern("SWS").pattern("WOW").define('O', ModItems.OWL_FEATHER.get()).define('R', ModItems.REINFORCED_PAPER_SAPPHIRE.get()).define('W', Items.STICK).define('S', Items.STRING).group("gliders").unlockedBy("has_crafting_table", has(Blocks.CRAFTING_TABLE)).save(consumer);

        // Sapphire Tools
        ShapedRecipeBuilder.shaped(ModItems.SAPPHIRE_AXE.get()).define('#', Items.STICK).define('X', ModItems.SAPPHIRE_GEM.get()).pattern("XX").pattern("X#").pattern(" #").unlockedBy("has_sapphire", has(ModItems.SAPPHIRE_GEM.get())).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.SAPPHIRE_PICKAXE.get()).define('#', Items.STICK).define('X', ModItems.SAPPHIRE_GEM.get()).pattern("XXX").pattern(" # ").pattern(" # ").unlockedBy("has_sapphire", has(ModItems.SAPPHIRE_GEM.get())).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.SAPPHIRE_SHOVEL.get()).define('#', Items.STICK).define('X', ModItems.SAPPHIRE_GEM.get()).pattern("X").pattern("#").pattern("#").unlockedBy("has_sapphire", has(ModItems.SAPPHIRE_GEM.get())).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.SAPPHIRE_SWORD.get()).define('#', Items.STICK).define('X', ModItems.SAPPHIRE_GEM.get()).pattern("X").pattern("X").pattern("#").unlockedBy("has_sapphire", has(ModItems.SAPPHIRE_GEM.get())).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.SAPPHIRE_HOE.get()).define('#', Items.STICK).define('X', ModItems.SAPPHIRE_GEM.get()).pattern("XX").pattern(" #").pattern(" #").unlockedBy("has_sapphire", has(ModItems.SAPPHIRE_GEM.get())).save(consumer);


    }
}
