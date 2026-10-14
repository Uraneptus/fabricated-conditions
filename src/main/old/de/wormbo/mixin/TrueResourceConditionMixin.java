package de.wormbo.mixin;

import de.wormbo.conditions.LoadCondition;
import de.wormbo.conditions.TrueLoadCondition;
import de.wormbo.converter.ConvertibleCondition;
import net.fabricmc.fabric.impl.resource.conditions.conditions.TrueResourceCondition;
import org.spongepowered.asm.mixin.Mixin;

/**
 * Adds Neoforge codec conversion for the TRUE condition, which has a direct equivalent in Neoforge.
 */
@SuppressWarnings("UnstableApiUsage")
@Mixin(TrueResourceCondition.class)
public abstract class TrueResourceConditionMixin implements ConvertibleCondition {
	@Override
	public LoadCondition forgedconditions_convert() {
		return TrueLoadCondition.INSTANCE;
	}
}
