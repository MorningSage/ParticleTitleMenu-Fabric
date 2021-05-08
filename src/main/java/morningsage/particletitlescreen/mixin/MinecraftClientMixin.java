package morningsage.particletitlescreen.mixin;

import morningsage.particletitlescreen.events.ResolutionChangedEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(
        at = @At("RETURN"),
        method = "onResolutionChanged"
    )
    public void onResolutionChanged(CallbackInfo callbackInfo) {
        ResolutionChangedEvent.ON_CHANGED.invoker().onChanged();
    }
}
