package dev.uraneptus.fabricatedconditions.conditions;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.uraneptus.fabricatedconditions.FabricatedConditions;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public record FabricAndCondition(List<LoadCondition> conditions) implements LoadCondition {
    public static final MapCodec<FabricAndCondition> CODEC = RecordCodecBuilder.mapCodec(
            builder -> builder
                    .group(
                            LIST_CODEC.fieldOf("values").forGetter(FabricAndCondition::conditions)
                    )
                    .apply(builder, FabricAndCondition::new));

    @Override
    public MapCodec<? extends LoadCondition> codec() {
        return CODEC;
    }
}
