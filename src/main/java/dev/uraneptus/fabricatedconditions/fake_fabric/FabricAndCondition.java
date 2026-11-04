package dev.uraneptus.fabricatedconditions.fake_fabric;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.List;

public record FabricAndCondition(List<FakeFabricCondition> conditions) implements FakeFabricCondition {
    public static final MapCodec<FabricAndCondition> CODEC = RecordCodecBuilder.mapCodec(
            builder -> builder
                    .group(
                            LIST_CODEC.fieldOf("values").forGetter(FabricAndCondition::conditions)
                    )
                    .apply(builder, FabricAndCondition::new));

    @Override
    public MapCodec<? extends FakeFabricCondition> codec() {
        return CODEC;
    }
}
