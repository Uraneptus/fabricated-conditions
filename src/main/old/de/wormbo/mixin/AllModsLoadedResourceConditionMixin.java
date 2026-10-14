package de.wormbo.mixin;

import de.wormbo.conditions.AndLoadCondition;
import de.wormbo.conditions.LoadCondition;
import de.wormbo.conditions.ModLoadedLoadCondition;
import de.wormbo.converter.ConvertibleCondition;
import net.fabricmc.fabric.impl.resource.conditions.conditions.AllModsLoadedResourceCondition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

/**
 * Adds Neoforge codec conversion for the "all_mods_loaded" condition. Depending on the number of specified mod IDs,
 * the conversion either returns a single "mod_loaded" condition or an AND condition wrapping one "mod_loaded"
 * condition for each specified mod ID.
 */
@SuppressWarnings("UnstableApiUsage")
@Mixin(AllModsLoadedResourceCondition.class)
public abstract class AllModsLoadedResourceConditionMixin implements ConvertibleCondition {
	@Shadow
	public abstract List<String> modIds();

	@Override
	public LoadCondition forgedconditions_convert() {
		List<String> modIds = modIds();
		if (modIds.size() == 1) {
			return new ModLoadedLoadCondition(modIds.getFirst());
		}
		return new AndLoadCondition(List.copyOf(modIds.stream().map(ModLoadedLoadCondition::new).toList()));
	}
}
