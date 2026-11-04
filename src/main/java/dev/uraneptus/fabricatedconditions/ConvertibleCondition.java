package dev.uraneptus.fabricatedconditions;

import dev.uraneptus.fabricatedconditions.fake_fabric.FakeFabricCondition;
import org.jetbrains.annotations.Nullable;

/**
 * This interface should be added to all IConditions that have a FakeFabricCondition counterpart.
 * This is used on runtime to convert the ICondition to the FakeFabricCondition.
 */
public interface ConvertibleCondition {

    @Nullable
    default FakeFabricCondition fabricatedconditions_convert() {
        FabricatedConditionsMod.LOGGER.warn("Condition type not supported for conversion: {}", this.getClass());
        return null;
    }
}
