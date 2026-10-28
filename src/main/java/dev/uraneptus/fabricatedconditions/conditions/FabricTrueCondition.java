package dev.uraneptus.fabricatedconditions.conditions;

import com.mojang.serialization.MapCodec;

public class FabricTrueCondition implements LoadCondition {
    public static final FabricTrueCondition INSTANCE = new FabricTrueCondition();
    public static final MapCodec<FabricTrueCondition> CODEC = MapCodec.unit(INSTANCE).stable();

    private FabricTrueCondition() {
    }

    @Override
    public MapCodec<? extends LoadCondition> codec() {
        return CODEC;
    }
}
