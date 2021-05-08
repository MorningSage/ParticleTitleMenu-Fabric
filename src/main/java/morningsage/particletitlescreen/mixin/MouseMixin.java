package morningsage.particletitlescreen.mixin;

import morningsage.particletitlescreen.events.MouseEvents;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mouse.class)
@Environment(EnvType.CLIENT)
public class MouseMixin {
    @Inject(
        at = @At("RETURN"),
        method = "onCursorPos"
    )
    private void onCursorPos(long window, double x, double y, CallbackInfo callbackInfo) {
        MouseEvents.ON_MOVE.invoker().onMove(window, x, y);
    }
}
