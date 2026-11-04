package dev.uraneptus.fabricatedconditions.mixin;

import dev.uraneptus.fabricatedconditions.ConvertibleCondition;
import dev.uraneptus.fabricatedconditions.fake_fabric.FabricTrueCondition;
import dev.uraneptus.fabricatedconditions.fake_fabric.FakeFabricCondition;
import net.neoforged.neoforge.common.conditions.TrueCondition;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TrueCondition.class)
public class TrueConditionMixin implements ConvertibleCondition {

    @Override
    public FakeFabricCondition fabricatedconditions_convert() {
        return FabricTrueCondition.INSTANCE;
    }

}
