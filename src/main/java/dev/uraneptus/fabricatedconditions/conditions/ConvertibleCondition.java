package dev.uraneptus.fabricatedconditions.conditions;

import dev.uraneptus.fabricatedconditions.FabricatedConditions;
import org.jetbrains.annotations.Nullable;

public interface ConvertibleCondition {

    @Nullable
    default LoadCondition fabricatedconditions_convert() {
        FabricatedConditions.LOGGER.warn("Condition type not supported for conversion: {}", this.getClass());
        return null;
    }
}
