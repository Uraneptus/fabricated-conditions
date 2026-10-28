package dev.uraneptus.fabricatedconditions.mixin;

import dev.uraneptus.fabricatedconditions.conditions.ConvertibleCondition;
import dev.uraneptus.fabricatedconditions.conditions.FabricTrueCondition;
import dev.uraneptus.fabricatedconditions.conditions.LoadCondition;
import net.neoforged.neoforge.common.conditions.TrueCondition;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TrueCondition.class)
public class TrueConditionMixin implements ConvertibleCondition {

    @Override
    public LoadCondition fabricatedconditions_convert() {
        return FabricTrueCondition.INSTANCE;
    }

}
