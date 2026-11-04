package dev.uraneptus.fabricatedconditions;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.uraneptus.fabricatedconditions.fake_fabric.FakeFabricCondition;
import net.minecraft.resources.RegistryOps;
import net.neoforged.neoforge.common.conditions.ConditionalOps;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.WithConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DataProviderHandler {

    public static <T> void addFabricConditions(T value, RegistryOps<JsonElement> registryops, JsonElement element) {
        if (element instanceof JsonObject obj) {
            var conditionsElement = obj.get(ConditionalOps.DEFAULT_CONDITIONS_KEY);
            if (conditionsElement != null) {
                Optional<WithConditions<T>> decoded = (Optional<WithConditions<T>>) value;

                if (decoded.isPresent()) {
                    var conditions = decoded.get().conditions();
                    List<FakeFabricCondition> fakeFabricConditions = new ArrayList<>();
                    for (ICondition condition : conditions) {
                        var convertable = (ConvertibleCondition) condition;
                        var loadCondition = convertable.fabricatedconditions_convert();
                        if (loadCondition == null) continue;
                        fakeFabricConditions.add(loadCondition);
                    }
                    JsonElement result = FakeFabricCondition.LIST_CODEC.encodeStart(registryops, fakeFabricConditions).getOrThrow();
                    obj.add(FabricatedConditionsMod.CONDITIONS_KEY, result);
                }
            }
        }
    }
}
