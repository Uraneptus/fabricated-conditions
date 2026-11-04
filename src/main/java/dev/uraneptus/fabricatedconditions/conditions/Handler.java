package dev.uraneptus.fabricatedconditions.conditions;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import dev.uraneptus.fabricatedconditions.FabricatedConditions;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.resources.RegistryOps;
import net.neoforged.neoforge.common.conditions.ConditionalOps;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.WithConditions;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Handler {

    public static <T> void addFabricConditions(CachedOutput output, HolderLookup.Provider registries, Codec<T> codec, T value, Path path, RegistryOps<JsonElement> registryops, JsonElement element) {
        if (element instanceof JsonObject obj) {
            var conditionsElement = obj.get(ConditionalOps.DEFAULT_CONDITIONS_KEY);
            if (conditionsElement != null) {
                Optional<WithConditions<T>> decoded = (Optional<WithConditions<T>>) value;

                if (decoded.isPresent()) {
                    var conditions = decoded.get().conditions();
                    List<LoadCondition> loadConditions = new ArrayList<>();
                    for (ICondition condition : conditions) {
                        var convertable = (ConvertibleCondition) condition;
                        var loadCondition = convertable.fabricatedconditions_convert();
                        if (loadCondition == null) continue;
                        loadConditions.add(loadCondition);
                    }
                    JsonElement result = LoadCondition.LIST_CODEC.encodeStart(registryops, loadConditions).getOrThrow();
                    obj.add(FabricatedConditions.CONDITIONS_KEY, result);
                }
            }
        }
    }
}
