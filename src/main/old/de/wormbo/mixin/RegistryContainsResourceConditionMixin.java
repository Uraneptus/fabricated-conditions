package de.wormbo.mixin;

import de.wormbo.ForgedConditions;
import de.wormbo.conditions.AndLoadCondition;
import de.wormbo.conditions.ItemExistsLoadCondition;
import de.wormbo.conditions.LoadCondition;
import de.wormbo.converter.ConvertibleCondition;
import net.fabricmc.fabric.impl.resource.conditions.conditions.RegistryContainsResourceCondition;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

/**
 * Adds Neoforge codec conversion for the "registry_contains" resource condition. Conversion is only supported if the
 * resource condition tests against the items registry. Depending on the number of specified item IDs, the conversion
 * either returns a single "item_exists" condition or an AND condition wrapping one "item_exists" condition for each
 * specified item ID.
 */
@SuppressWarnings("UnstableApiUsage")
@Mixin(RegistryContainsResourceCondition.class)
public abstract class RegistryContainsResourceConditionMixin implements ConvertibleCondition {
	@Shadow
	public abstract ResourceLocation registry();

	@Shadow
	public abstract List<ResourceLocation> entries();

	@Override
	public LoadCondition forgedconditions_convert() {
		if (!Registries.ITEM.location().equals(registry())) {
			ForgedConditions.LOGGER.warn(
					"Unsupported registry '{}' for registry_contains condition, only '{}' is supported",
					registry(), Registries.ITEM.location());
			return null;
		}
		List<ResourceLocation> entries = entries();
		if (entries.size() == 1) {
			return new ItemExistsLoadCondition(entries.getFirst());
		}
		return new AndLoadCondition(List.copyOf(entries.stream().map(ItemExistsLoadCondition::new).toList()));
	}
}
