package dev.uraneptus.fabricatedconditions;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.MapCodec;
import dev.uraneptus.fabricatedconditions.fake_fabric.FabricAndCondition;
import dev.uraneptus.fabricatedconditions.fake_fabric.FabricAnyModsLoadedCondition;
import dev.uraneptus.fabricatedconditions.fake_fabric.FabricTrueCondition;
import dev.uraneptus.fabricatedconditions.fake_fabric.FakeFabricCondition;
import dev.uraneptus.fabricatedconditions.test.FabricatedConditionsExampleDataGenerator;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.data.loading.DatagenModLoader;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;
import org.slf4j.Logger;

@Mod(FabricatedConditionsMod.MODID)
public class FabricatedConditionsMod {
    public static final String MODID = "fabricatedconditions";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final String CONDITIONS_KEY = "fabric:load_conditions";

    public static ResourceLocation fcId(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    public static final ResourceKey<Registry<MapCodec<? extends FakeFabricCondition>>> CONDITION_CODECS_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(MODID, "condition_codecs"));
    public static final Registry<MapCodec<? extends FakeFabricCondition>> CONDITION_SERIALIZERS = new RegistryBuilder<>(CONDITION_CODECS_KEY).create();


    private static final DeferredRegister<MapCodec<? extends FakeFabricCondition>> CONDITION_CODECS = DeferredRegister.create(CONDITION_CODECS_KEY, "fabric");

    public static final DeferredHolder<MapCodec<? extends FakeFabricCondition>, MapCodec<FabricAndCondition>> AND_CONDITION = CONDITION_CODECS.register("and", () -> FabricAndCondition.CODEC);
    public static final DeferredHolder<MapCodec<? extends FakeFabricCondition>, MapCodec<FabricTrueCondition>> TRUE_CONDITION = CONDITION_CODECS.register("true", () -> FabricTrueCondition.CODEC);
    public static final DeferredHolder<MapCodec<? extends FakeFabricCondition>, MapCodec<FabricAnyModsLoadedCondition>> ANY_MODS_LOADED_CONDITION = CONDITION_CODECS.register("any_mods_loaded", () -> FabricAnyModsLoadedCondition.CODEC);


    public FabricatedConditionsMod(IEventBus modEventBus) {
        if (FMLLoader.isProduction()) {
            throw new RuntimeException("Fabricated Conditions should not be used outside of the dev environment!");

        }

        if (!DatagenModLoader.isRunningDataGen()) {
            throw new RuntimeException("Fabricated Conditions should not be used outside of DataGen!");
        }

        modEventBus.addListener(FabricatedConditionsMod::registerRegistries);
        modEventBus.addListener(FabricatedConditionsExampleDataGenerator::gatherData);

        CONDITION_CODECS.register(modEventBus);
    }

    private static void registerRegistries(NewRegistryEvent event) {
        event.register(CONDITION_SERIALIZERS);
    }
}
