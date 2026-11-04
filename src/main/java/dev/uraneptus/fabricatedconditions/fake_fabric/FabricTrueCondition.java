package dev.uraneptus.fabricatedconditions.fake_fabric;

import com.mojang.serialization.MapCodec;

public class FabricTrueCondition implements FakeFabricCondition {
    public static final FabricTrueCondition INSTANCE = new FabricTrueCondition();
    public static final MapCodec<FabricTrueCondition> CODEC = MapCodec.unit(INSTANCE).stable();

    private FabricTrueCondition() {
    }

    @Override
    public MapCodec<? extends FakeFabricCondition> codec() {
        return CODEC;
    }
}
