package dev.uraneptus.fabricatedconditions;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.conditions.*;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static dev.uraneptus.fabricatedconditions.FabricatedConditions.fcId;

public class FabricatedConditionsExampleDataGenerator {

    public static void gatherData(GatherDataEvent event) {
        var lookupProvider = event.getLookupProvider();
        var packOutput = event.getGenerator().getPackOutput();

        event.getGenerator().addProvider(event.includeServer(), new FCRecipeProvider(packOutput, lookupProvider));
    }

    static class FCRecipeProvider extends RecipeProvider {

        public FCRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
            super(output, registries);
        }

        @Override
        protected void buildRecipes(RecipeOutput recipeOutput) {

            ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Blocks.END_PORTAL_FRAME)
                    .define('E', Blocks.END_STONE)
                    .define('S', Items.NETHER_STAR)
                    .pattern(" E ")
                    .pattern("ESE")
                    .pattern(" E ")
                    .unlockedBy("has_Star", has(Items.NETHER_STAR))
                    .save(recipeOutput.withConditions(new AndCondition(List.of(
                            new ModLoadedCondition("craftable_endportal_frames"),
                            new ModLoadedCondition("better-end-access")
                    ))), fcId("example-recipe"));
                    /*
                    .save(withConditions(exporter,
                                    new AndResourceCondition(List.of(
                                            new AnyModsLoadedResourceCondition(
                                                    List.of("craftable_endportal_frames", "better-end-access")),
                                            new RegistryContainsResourceCondition(Registries.ITEM.location(),
                                                    BuiltInRegistries.ITEM.getKey(Items.NETHER_STAR),
                                                    BuiltInRegistries.ITEM.getKey(Items.END_STONE)),
                                            // this "block exists" condition will be ignored for conversion:
                                            new RegistryContainsResourceCondition(Registries.BLOCK.location(),
                                                    BuiltInRegistries.BLOCK.getKey(Blocks.END_PORTAL_FRAME)),
                                            // this NOT condition causes a double negation in the conversion:
                                            new NotResourceCondition(
                                                    new TagsPopulatedResourceCondition(ItemTags.PICKAXES,
                                                            ItemTags.SHOVELS))
                                    ))),
                            fcId("example-recipe"))*/
        }
    }
}
