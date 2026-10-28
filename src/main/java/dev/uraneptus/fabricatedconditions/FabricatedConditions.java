package dev.uraneptus.fabricatedconditions;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.MapCodec;
import dev.uraneptus.fabricatedconditions.conditions.FabricAndCondition;
import dev.uraneptus.fabricatedconditions.conditions.FabricAnyModsLoadedCondition;
import dev.uraneptus.fabricatedconditions.conditions.FabricTrueCondition;
import dev.uraneptus.fabricatedconditions.conditions.LoadCondition;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.common.conditions.AndCondition;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.registries.*;
import org.slf4j.Logger;

@Mod(FabricatedConditions.MODID)
public class FabricatedConditions {
    public static final String MODID = "fabricatedconditions";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final String CONDITIONS_KEY = "fabric:load_conditions";

    public static ResourceLocation fcId(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    public static ResourceLocation fabricId(String path) {
        return ResourceLocation.fromNamespaceAndPath("fabric", path);
    }

    public static final ResourceKey<Registry<MapCodec<? extends LoadCondition>>> CONDITION_CODECS_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(MODID, "condition_codecs"));
    public static final Registry<MapCodec<? extends LoadCondition>> CONDITION_SERIALIZERS = new RegistryBuilder<>(CONDITION_CODECS_KEY).create();


    private static final DeferredRegister<MapCodec<? extends LoadCondition>> CONDITION_CODECS = DeferredRegister.create(CONDITION_CODECS_KEY, "fabric");

    public static final DeferredHolder<MapCodec<? extends LoadCondition>, MapCodec<FabricAndCondition>> AND_CONDITION = CONDITION_CODECS.register("and", () -> FabricAndCondition.CODEC);
    public static final DeferredHolder<MapCodec<? extends LoadCondition>, MapCodec<FabricTrueCondition>> TRUE_CONDITION = CONDITION_CODECS.register("true", () -> FabricTrueCondition.CODEC);
    public static final DeferredHolder<MapCodec<? extends LoadCondition>, MapCodec<FabricAnyModsLoadedCondition>> ANY_MODS_LOADED_CONDITION = CONDITION_CODECS.register("any_mods_loaded", () -> FabricAnyModsLoadedCondition.CODEC);


    public FabricatedConditions(IEventBus modEventBus, ModContainer modContainer) {
        if (FMLLoader.isProduction()) {
            LOGGER.error("Fabricated Conditions should not be used outside of a development environment!");
            return;
        }

        modEventBus.addListener(FabricatedConditions::registerRegistries);
        modEventBus.addListener(FabricatedConditionsExampleDataGenerator::gatherData);

        CONDITION_CODECS.register(modEventBus);
    }

    private static void registerRegistries(NewRegistryEvent event) {
        event.register(CONDITION_SERIALIZERS);
    }
}
