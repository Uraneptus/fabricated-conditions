package dev.uraneptus.fabricatedconditions.api;

import com.mojang.serialization.MapCodec;
import dev.uraneptus.fabricatedconditions.FabricatedConditionsMod;
import dev.uraneptus.fabricatedconditions.fake_fabric.FakeFabricCondition;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.data.loading.DatagenModLoader;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class FabricatedConditionsAPI {
    private final String modId;
    private final DeferredRegister<MapCodec<? extends ICondition>> NEO_CONDITIONS_CODECS;
    private final DeferredRegister<MapCodec<? extends FakeFabricCondition>> FAKE_FABRIC_CONDITION_CODECS;

    public FabricatedConditionsAPI(String modId) {
        this.modId = modId;
        NEO_CONDITIONS_CODECS = DeferredRegister.create(NeoForgeRegistries.CONDITION_SERIALIZERS, modId);
        FAKE_FABRIC_CONDITION_CODECS = DeferredRegister.create(FabricatedConditionsMod.CONDITION_CODECS_KEY, modId);
    }

    public void registerFakeConditions(String name, Supplier<MapCodec<? extends ICondition>> neoVariant, Supplier<MapCodec<? extends FakeFabricCondition>> fabricVariant) {
        NEO_CONDITIONS_CODECS.register(name, neoVariant);
        FAKE_FABRIC_CONDITION_CODECS.register(name, fabricVariant);
    }

    //Never call this outside datagen!!!
    public void registerAPI(IEventBus bus) {
        if (!DatagenModLoader.isRunningDataGen()) return;
        NEO_CONDITIONS_CODECS.register(bus);
        FAKE_FABRIC_CONDITION_CODECS.register(bus);
    }

    public String getModId() {
        return modId;
    }
}
