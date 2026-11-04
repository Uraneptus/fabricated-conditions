package dev.uraneptus.fabricatedconditions.fake_fabric;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import dev.uraneptus.fabricatedconditions.FabricatedConditionsMod;

import java.util.List;
import java.util.function.Function;

public interface FakeFabricCondition {
    Codec<FakeFabricCondition> CODEC = FabricatedConditionsMod.CONDITION_SERIALIZERS.byNameCodec()
            .dispatch("condition", FakeFabricCondition::codec, Function.identity());
    Codec<List<FakeFabricCondition>> LIST_CODEC = CODEC.listOf();

    MapCodec<? extends FakeFabricCondition> codec();
}
