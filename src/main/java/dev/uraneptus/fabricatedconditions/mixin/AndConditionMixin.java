package dev.uraneptus.fabricatedconditions.mixin;

import dev.uraneptus.fabricatedconditions.FabricatedConditions;
import dev.uraneptus.fabricatedconditions.conditions.ConvertibleCondition;
import dev.uraneptus.fabricatedconditions.conditions.FabricAndCondition;
import dev.uraneptus.fabricatedconditions.conditions.LoadCondition;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.conditions.AndCondition;
import net.neoforged.neoforge.common.conditions.ICondition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;
import java.util.Objects;

@Mixin(AndCondition.class)
public abstract class AndConditionMixin implements ConvertibleCondition {
    @Shadow
    public abstract List<ICondition> children();

    @Override
    public LoadCondition fabricatedconditions_convert() {
        List<LoadCondition> children = children().stream()
                .map(ConvertibleCondition.class::cast)
                .map(ConvertibleCondition::fabricatedconditions_convert)
                .filter(Objects::nonNull)
                .toList();
        if (children.isEmpty()) {
            FabricatedConditions.LOGGER.warn("Empty AND condition list");
        }
        return new FabricAndCondition(children);
    }
}
