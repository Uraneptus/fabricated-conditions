package dev.uraneptus.fabricatedconditions.mixin;

import dev.uraneptus.fabricatedconditions.conditions.ConvertibleCondition;
import dev.uraneptus.fabricatedconditions.conditions.FabricAnyModsLoadedCondition;
import dev.uraneptus.fabricatedconditions.conditions.LoadCondition;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(ModLoadedCondition.class)
public abstract class ModLoadedConditionMixin implements ConvertibleCondition {

    @Shadow
    public abstract String modid();

    @Override
    public LoadCondition fabricatedconditions_convert() {
        //TODO check if there's a way to respect parent or condition
        return new FabricAnyModsLoadedCondition(List.of(modid()));
    }
}
