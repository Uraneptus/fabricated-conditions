package dev.uraneptus.fabricatedconditions.mixin;

import com.google.gson.JsonElement;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import dev.uraneptus.fabricatedconditions.FabricatedConditions;
import dev.uraneptus.fabricatedconditions.conditions.Handler;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.RegistryOps;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

@Mixin(DataProvider.class)
public interface DataProviderMixin {

    //works
    @Dynamic("lambda method passed to Util.make for FIXED_ORDER_FIELDS")
    @Inject(method = "lambda$static$0(Lit/unimi/dsi/fastutil/objects/Object2IntOpenHashMap;)V", at = @At("HEAD"))
    private static void fabricatedconditions_injectFabricConditionsSortOrder(Object2IntOpenHashMap<String> map, CallbackInfo ci) {
        map.put(FabricatedConditions.CONDITIONS_KEY, -100);
    }

    @Inject(method = "saveStable(Lnet/minecraft/data/CachedOutput;Lnet/minecraft/core/HolderLookup$Provider;Lcom/mojang/serialization/Codec;Ljava/lang/Object;Ljava/nio/file/Path;)Ljava/util/concurrent/CompletableFuture;",
            at = @At("TAIL"),
            locals = LocalCapture.CAPTURE_FAILSOFT
    )
    private static <T> void addFabricEConditions(CachedOutput output, HolderLookup.Provider registries, Codec<T> codec, T value, Path path, CallbackInfoReturnable<CompletableFuture<?>> cir, RegistryOps<JsonElement> registryops, JsonElement element) {
        Handler.addFabricConditions(output, registries, codec, value, path, registryops, element);
    }
}
