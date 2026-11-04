package dev.uraneptus.fabricatedconditions.mixin;

import dev.uraneptus.fabricatedconditions.ConvertibleCondition;
import net.neoforged.neoforge.common.conditions.ICondition;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ICondition.class)
public interface IConditionMixin extends ConvertibleCondition {



}
