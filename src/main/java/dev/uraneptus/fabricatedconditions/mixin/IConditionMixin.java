package dev.uraneptus.fabricatedconditions.mixin;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.DynamicOps;
import dev.uraneptus.fabricatedconditions.FabricatedConditions;
import dev.uraneptus.fabricatedconditions.conditions.ConvertibleCondition;
import dev.uraneptus.fabricatedconditions.conditions.Handler;
import dev.uraneptus.fabricatedconditions.conditions.LoadCondition;
import net.neoforged.neoforge.common.conditions.ICondition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ICondition.class)
public interface IConditionMixin extends ConvertibleCondition {



}
