package dev.uraneptus.fabricatedconditions.conditions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import dev.uraneptus.fabricatedconditions.FabricatedConditions;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.List;
import java.util.function.Function;

public interface LoadCondition {
    Codec<LoadCondition> CODEC = FabricatedConditions.CONDITION_SERIALIZERS.byNameCodec()
            .dispatch("condition", LoadCondition::codec, Function.identity());
    Codec<List<LoadCondition>> LIST_CODEC = CODEC.listOf();

    MapCodec<? extends LoadCondition> codec();
}
