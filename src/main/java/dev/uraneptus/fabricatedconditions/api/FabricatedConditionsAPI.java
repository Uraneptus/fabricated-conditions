package dev.uraneptus.fabricatedconditions.api;

import com.mojang.serialization.MapCodec;
import dev.uraneptus.fabricatedconditions.FabricatedConditions;
import dev.uraneptus.fabricatedconditions.conditions.LoadCondition;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class FabricatedConditionsAPI {
    private final String modId;
    private final DeferredRegister<MapCodec<? extends ICondition>> NEO_CONDITIONS_CODECS;
    private final DeferredRegister<MapCodec<? extends LoadCondition>> FAKE_FABRIC_CONDITION_CODECS;

    public FabricatedConditionsAPI(String modId) {
        this.modId = modId;
        NEO_CONDITIONS_CODECS = DeferredRegister.create(NeoForgeRegistries.CONDITION_SERIALIZERS, modId);
        FAKE_FABRIC_CONDITION_CODECS = DeferredRegister.create(FabricatedConditions.CONDITION_CODECS_KEY, modId);
    }

    public void registerFakeConditions(String name, Supplier<MapCodec<? extends ICondition>> neoVariant, Supplier<MapCodec<? extends LoadCondition>> fabricVariant) {
        NEO_CONDITIONS_CODECS.register(name, neoVariant);
        FAKE_FABRIC_CONDITION_CODECS.register(name, fabricVariant);
    }

    //Never call this outside datagen!!!
    public void registerAPI(IEventBus bus) {
        NEO_CONDITIONS_CODECS.register(bus);
        FAKE_FABRIC_CONDITION_CODECS.register(bus);
    }

    public String getModId() {
        return modId;
    }
}
