package dev.uraneptus.fabricatedconditions.conditions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.List;

public record FabricAnyModsLoadedCondition(List<String> modIds) implements LoadCondition {
    public static final MapCodec<FabricAnyModsLoadedCondition> CODEC = RecordCodecBuilder.mapCodec(
            builder -> builder.group(
                            Codec.STRING.listOf().fieldOf("values").forGetter(FabricAnyModsLoadedCondition::modIds)
                    )
                    .apply(builder, FabricAnyModsLoadedCondition::new));

    @Override
    public MapCodec<? extends LoadCondition> codec() {
        return CODEC;
    }
}
